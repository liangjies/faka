package cn.liangjies.faka.controller;

import cn.liangjies.faka.entity.BO.OrderBo;
import cn.liangjies.faka.entity.OrderOid;
import cn.liangjies.faka.entity.TOrder;
import cn.liangjies.faka.entity.TProducts;
import cn.liangjies.faka.service.ConfigService;
import cn.liangjies.faka.service.TOrderService;
import cn.liangjies.faka.service.TProductsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequestMapping("/product/order")
public class BuyController {
    @Resource
    private ConfigService configService;

    @Resource
    private TProductsService tProductsService;

    @Resource
    private TOrderService tOrderService;

    /**
     * 获取客户端ip地址
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress="";
        }
        // ipAddress = this.getRequest().getRemoteAddr();

        return ipAddress;
    }


    @RequestMapping(value = ("/buy/"), method = RequestMethod.POST)
    @ResponseBody
    public OrderBo index(HttpServletRequest request) throws UnsupportedEncodingException {

        int number = Integer.parseInt(request.getParameter("number"));
        String qq = request.getParameter("qq");
        String chapwd = request.getParameter("chapwd");
        String price = request.getParameter("price");
        String qty = request.getParameter("qty");
        String stockcontrol = request.getParameter("stockcontrol");
        String limitorderqty = request.getParameter("limitorderqty");
        int pid = Integer.parseInt(request.getParameter("pid"));

        OrderBo orderBo = new OrderBo();

        Map configmap = new HashMap();
        configmap = configService.getConfig();
        Object config = configmap.get("querycontactswitch");
        if(pid>0 && number>0){
            if(Integer.parseInt(configmap.get("querycontactswitch").toString())>0){
                if(chapwd.length()==0){
                    orderBo.setCode(1006);
                    orderBo.setMsg("丢失参数");
                    return orderBo;
                }
            }
            TProducts tProducts = tProductsService.queryById(pid);
            if(tProducts.getActive()==1 && tProducts.getIsdelete()==0){
                //库存控制
                if(tProducts.getStockcontrol()==1 && tProducts.getQty()<1){
                    orderBo.setCode(1003);
                    orderBo.setMsg("'库存不足'");
                    return orderBo;
                }
                if(tProducts.getStockcontrol()==1 && number>tProducts.getQty()){
                    orderBo.setCode(1004);
                    orderBo.setMsg("'库存不足'");
                    return orderBo;
                }
                if(configmap.get("limitorderqty").toString().isEmpty() && Integer.parseInt(configmap.get("limitorderqty").toString())<number){
                    orderBo.setCode(1005);
                    orderBo.setMsg("''下单数量超限''");
                    return orderBo;
                }
                //预留下单未付款的处理判断

                //生成orderid
                String prefix = "zlkb";  //订单前缀
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter dateTimeFormatter =   DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

                String time = dateTimeFormatter.format(now);
                int max=10000;
                int min=99999;
                Random random = new Random();
                String rand = Integer.toString(random.nextInt(max)%(max-min+1) + min);
                System.out.println(rand);
                System.out.println(time);
                String orderid = prefix + time + rand;

                String email = qq + "@qq.com";

                //计算订单价格
                double money = tProducts.getPrice()*number;

                //开始下单，入库
                TOrder tOrder = new TOrder();
                tOrder.setOrderid(orderid);
                tOrder.setUserid(0);
                tOrder.setEmail(email);
                tOrder.setQq(qq);
                tOrder.setPid(pid);
                tOrder.setProductname(tProducts.getName());
                tOrder.setPrice(tProducts.getPrice());
                tOrder.setNumber(number);
                tOrder.setMoney(money);
                tOrder.setChapwd(chapwd);
                tOrder.setIp(getIpAddr(request));
                tOrder.setStatus(0);
                int unixtime = (int) (System.currentTimeMillis()/1000);
                tOrder.setAddtime(unixtime);
                tOrder.setPaytime(0);
                tOrder.setIsdelete(0);


                if(tOrderService.insert(tOrder)){
                    Base64.Encoder encoder = Base64.getEncoder();
                    String oid = encoder.encodeToString(orderid.getBytes("UTF-8"));

                    orderBo.setCode(1);
                    orderBo.setMsg("''下单成功''");
                    OrderOid orderOid = new OrderOid();
                    orderOid.setOid(oid);
                    orderBo.setData(orderOid);
                    return orderBo;
                }
            }

        }else{
            orderBo.setCode(1000);
            orderBo.setMsg("丢失参数");
        }
        return orderBo;
    }

    @GetMapping("/pay/")
    public String pay(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        String base64oid = request.getParameter("oid");
        Base64.Decoder decoder = Base64.getDecoder();
        String oid = new String(decoder.decode(base64oid),"UTF-8");


        TOrder tOrder = tOrderService.queryByOrderid(oid);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String times = format.format(new Date(tOrder.getAddtime() * 1000L));


        model.addAttribute("config", configService.getConfig());
        model.addAttribute("version", configService.getVersion());
        model.addAttribute("base64oid",base64oid);
        model.addAttribute("order",tOrder);
        model.addAttribute("addtime",times);

        //String oid = encoder.encodeToString(orderid.getBytes("UTF-8"));


        return "hyacinth/order/pay";
    }
}
