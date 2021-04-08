package cn.liangjies.faka.controller;

import cn.liangjies.faka.entity.*;
import cn.liangjies.faka.entity.BO.OrderBo;
import cn.liangjies.faka.entity.BO.PayBo;
import cn.liangjies.faka.service.TOrderService;
import cn.liangjies.faka.service.TProductsCardService;
import cn.liangjies.faka.service.TProductsService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.model.ExtendParams;
import com.alipay.demo.trade.model.GoodsDetail;
import com.alipay.demo.trade.model.builder.AlipayTradePrecreateRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradeQueryRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.model.result.AlipayF2FQueryResult;
import com.alipay.demo.trade.service.AlipayMonitorService;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayMonitorServiceImpl;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.alipay.demo.trade.service.impl.AlipayTradeWithHBServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/product")
public class AlipayController {
    private static Log log = LogFactory.getLog(AlipayController.class);

    // 支付宝当面付2.0服务
    private static AlipayTradeService   tradeService;

    // 支付宝当面付2.0服务（集成了交易保障接口逻辑）
    private static AlipayTradeService   tradeWithHBService;

    // 支付宝交易保障接口服务，供测试接口api使用，请先阅读readme.txt
    private static AlipayMonitorService monitorService;

    @Resource
    private TOrderService tOrderService;

    @Resource
    private TProductsCardService tProductsCardService;

    @Resource
    private TProductsService tProductsService;

    @Autowired
    private JavaMailSender mailSender;
    /**
     * 日期格式字符串转换成时间戳
     * @param date_str 字符串日期
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static int date2TimeStamp(String date_str,String format){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return Integer.parseInt(String.valueOf(sdf.parse(date_str).getTime()/1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    static {
        /** 一定要在创建AlipayTradeService之前调用Configs.init()设置默认参数
         *  Configs会读取classpath下的zfbinfo.properties文件配置信息，如果找不到该文件则确认该文件是否在classpath目录
         */
        Configs.init("zfbinfo.properties");

        /** 使用Configs提供的默认参数
         *  AlipayTradeService可以使用单例或者为静态成员对象，不需要反复new
         */
        tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();

        // 支付宝当面付2.0服务（集成了交易保障接口逻辑）
        tradeWithHBService = new AlipayTradeWithHBServiceImpl.ClientBuilder().build();

        /** 如果需要在程序中覆盖Configs提供的默认参数, 可以使用ClientBuilder类的setXXX方法修改默认参数 否则使用代码中的默认设置 */
        monitorService = new AlipayMonitorServiceImpl.ClientBuilder()
                .setGatewayUrl("http://mcloudmonitor.com/gateway.do").setCharset("GBK")
                .setFormat("json").build();
    }

    // 简单打印应答
    private void dumpResponse(AlipayResponse response) {
        if (response != null) {
            log.info(String.format("code:%s, msg:%s", response.getCode(), response.getMsg()));
            if (StringUtils.isNotEmpty(response.getSubCode())) {
                log.info(String.format("subCode:%s, subMsg:%s", response.getSubCode(),
                        response.getSubMsg()));
            }
            log.info("body:" + response.getBody());
        }
    }

    // 测试当面付2.0生成支付二维码
    @ResponseBody
    @RequestMapping(value = "/order/payajax",method = RequestMethod.POST)
    public PayBo test_trade_precreate(HttpServletRequest request) throws UnsupportedEncodingException {
        String paymethod = request.getParameter("paymethod");
        String base64oid = request.getParameter("oid");
        Base64.Decoder decoder = Base64.getDecoder();
        String oid = new String(decoder.decode(base64oid),"UTF-8");

        TOrder tOrder = tOrderService.queryByOrderid(oid);
        // (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
        // 需保证商户系统端不能重复，建议通过数据库sequence生成，
        String outTradeNo = oid;

        // (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店当面付扫码消费”
        String subject = tOrder.getProductname();

        // (必填) 订单总金额，单位为元，不能超过1亿元
        // 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
        String totalAmount = String.valueOf(tOrder.getMoney());

        // (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
        // 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
        String undiscountableAmount = "0";

        // 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
        // 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
        String sellerId = "";

        // 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品2件共15.00元"
        String body = "购买商品"+tOrder.getNumber()+"件共"+totalAmount+"元";

        // 商户操作员编号，添加此参数可以为商户操作员做销售统计
        String operatorId = "test_operator_id";

        // (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
        String storeId = "test_store_id";

        // 业务扩展参数，目前可添加由支付宝分配的系统商编号(通过setSysServiceProviderId方法)，详情请咨询支付宝技术支持
        ExtendParams extendParams = new ExtendParams();
        extendParams.setSysServiceProviderId("2088100200300400500");

        // 支付超时，定义为120分钟
        String timeoutExpress = "120m";

        // 商品明细列表，需填写购买商品详细信息，
        List<GoodsDetail> goodsDetailList = new ArrayList<GoodsDetail>();
        // 创建一个商品信息，参数含义分别为商品id（使用国标）、名称、单价（单位为分）、数量，如果需要添加商品类别，详见GoodsDetail
        GoodsDetail goods1 = GoodsDetail.newInstance(String.valueOf(tOrder.getPid()), tOrder.getProductname(),tOrder.getPrice().longValue(), tOrder.getNumber());
        // 创建好一个商品后添加至商品明细列表
        goodsDetailList.add(goods1);

        // 继续创建并添加第一条商品信息，用户购买的产品为“黑人牙刷”，单价为5.00元，购买了两件
        //GoodsDetail goods2 = GoodsDetail.newInstance("goods_id002", "xxx牙刷", 500, 2);
        //goodsDetailList.add(goods2);

        // 创建扫码支付请求builder，设置请求参数
        AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
                .setSubject(subject).setTotalAmount(totalAmount).setOutTradeNo(outTradeNo)
                .setUndiscountableAmount(undiscountableAmount).setSellerId(sellerId).setBody(body)
                .setOperatorId(operatorId).setStoreId(storeId).setExtendParams(extendParams)
                .setTimeoutExpress(timeoutExpress)
                //                .setNotifyUrl("http://www.test-notify-url.com")//支付宝服务器主动通知商户服务器里指定的页面http路径,根据需要设置
                .setGoodsDetailList(goodsDetailList);
        PayBo payBo =new PayBo();

        AlipayF2FPrecreateResult result = tradeService.tradePrecreate(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                log.info("支付宝预下单成功: )");

                AlipayTradePrecreateResponse response = result.getResponse();
                dumpResponse(response);

                JSONObject respJson = JSON.parseObject(response.getBody());
                JSONObject rsj = (JSONObject) respJson.get("alipay_trade_precreate_response");
                // 这里是阿里生成的二维码地址内容
                String qr_code = (String) rsj.get("qr_code");

                Alipay alipay = new Alipay();
                alipay.setMoney(tOrder.getMoney());
                alipay.setPaymethod("zfbf2f");
                alipay.setPayname("支付宝");
                alipay.setSubjumpurl(qr_code);
                alipay.setType(0);
                alipay.setOvertime(0);
                alipay.setSubjump(1);
                alipay.setQr("/product/order/showqr/?url=" + qr_code + "");


                payBo.setCode(1);
                payBo.setMsg("success");
                payBo.setData(alipay);


                break;

            case FAILED:
                log.error("支付宝预下单失败!!!");
                break;

            case UNKNOWN:
                log.error("系统异常，预下单状态未知!!!");
                break;

            default:
                log.error("不支持的交易状态，交易返回异常!!!");
                break;
        }
        return payBo;
    }


    /**
     * 订单查询(最主要用于查询订单的支付状态)
     * @param request 商户订单号
     * @return
     */
    @RequestMapping(value = "/query/pay", method = RequestMethod.POST)
    public OrderBo query(HttpServletRequest request) throws UnsupportedEncodingException {
        String base64oid = request.getParameter("oid");
        Base64.Decoder decoder = Base64.getDecoder();
        String oid = new String(decoder.decode(base64oid),"UTF-8");

        TOrder tOrder = tOrderService.queryByOrderid(oid);
        TOrder tOrderUpdate = new TOrder();
        OrderBo orderBo = new OrderBo();
        OrderOid orderOid = new OrderOid();

        AlipayTradeQueryRequestBuilder builder = new AlipayTradeQueryRequestBuilder()
                .setOutTradeNo(oid);
        AlipayF2FQueryResult result = tradeService.queryTradeResult(builder);
        switch (result.getTradeStatus()) {
            case SUCCESS:
                /**
                 * 更新订单状态
                 */
                System.out.println(tOrder.getStatus());
                if(tOrder.getStatus()==0){
                    System.out.println("运行");
                    AlipayTradeQueryResponse resp = result.getResponse();
                    JSONObject respJson = JSON.parseObject(resp.getBody());
                    JSONObject rsj = (JSONObject) respJson.get("alipay_trade_query_response");
                    String out_trade_no = (String) rsj.get("trade_no");
                    String total_amount = (String) rsj.get("total_amount");
                    String send_pay_date = (String) rsj.get("send_pay_date");
                    tOrderUpdate.setPaymoney(Double.parseDouble(total_amount));
                    tOrderUpdate.setPaymethod("zfbf2f");
                    tOrderUpdate.setTradeid(out_trade_no);
                    tOrderUpdate.setPaytime(date2TimeStamp(send_pay_date,"yyyy-MM-dd HH:mm:ss"));
                    tOrderUpdate.setId(tOrder.getId());
                    tOrderUpdate.setStatus(2);
                    //卡密入口
                    List<TProductsCard> tProductsCards = tProductsCardService.buyCard(tOrder.getPid(), tOrder.getNumber());
                    String kami = "";
                    for (TProductsCard tProductsCard : tProductsCards) {
                        kami = kami + tProductsCard.getCard()+"\n";
                    }
                    tOrderUpdate.setKami(kami);
                    //发送邮件
                    SimpleMailMessage message = new SimpleMailMessage();
                    message.setFrom("admin@faka.icewx.com");
                    message.setTo(tOrder.getEmail());
                    message.setSubject("商品购买成功");
                    message.setText("你购买的【"+tOrder.getProductname()+"】卡密为："+kami);
                    //减少库存增加销量
                    TProducts tProducts = tProductsService.queryById(tOrder.getPid());
                    tProducts.setQty(tProducts.getQty()-tOrder.getNumber());
                    tProducts.setQtySell(tProducts.getQtySell()+tOrder.getNumber());
                    tProductsService.update(tProducts);

                    try{
                        mailSender.send(message);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    tOrderService.update(tOrderUpdate);
                }
                /**
                 * 返回订单状态
                 */
                orderBo.setCode(1);
                orderBo.setMsg("success");
                orderOid.setOrderid(tOrder.getOrderid());
                orderBo.setData(orderOid);
                log.info("查询返回该订单支付成功: )");

                break;

            case FAILED:
                orderBo.setCode(1003);
                orderBo.setMsg("未支付");
                log.error("查询返回该订单支付失败!!!");
                break;

            case UNKNOWN:
                orderBo.setCode(1000);
                orderBo.setMsg("丢失参数");
                log.error("系统异常，订单支付状态未知!!!");
                break;

            default:
                orderBo.setCode(1000);
                orderBo.setMsg("丢失参数");
                log.error("不支持的交易状态，交易返回异常!!!");
                break;
        }
        return orderBo;
    }
}