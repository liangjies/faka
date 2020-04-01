package cn.liangjies.faka.controller;

import cn.liangjies.faka.entity.BO.KamiBo;
import cn.liangjies.faka.entity.BO.OrdersBo;
import cn.liangjies.faka.entity.TOrder;
import cn.liangjies.faka.service.ConfigService;
import cn.liangjies.faka.service.TOrderService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("product/query")
public class QueryController {
    /**
     * 服务对象
     */
    @Resource
    private ConfigService configService;

    @Resource
    private TOrderService tOrderService;

    @GetMapping(value = {"","/"})
    public String query(Model model, HttpServletRequest request) {
        String orderid = request.getParameter("orderid");
        String zlkbmethod = request.getParameter("zlkbmethod");
        model.addAttribute("AdminUser", configService.getAdminEmail());
        model.addAttribute("config", configService.getConfig());
        model.addAttribute("version", configService.getVersion());

        if (orderid == null || orderid.equals("")) {
            if (zlkbmethod != null && zlkbmethod.equals("contact")) {
                return "hyacinth/query/contact";
            } else if (zlkbmethod != null && zlkbmethod.equals("cookie")) {
                return "hyacinth/query/cookie";
            } else {
                return "hyacinth/query/orderid";
            }
        }
        TOrder tOrder = tOrderService.queryByOrderid(orderid);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String times = format.format(new Date(tOrder.getAddtime() * 1000L));

        model.addAttribute("order", tOrder);
        model.addAttribute("addtime",times);

        return "hyacinth/query/auto";
    }

    @ResponseBody
    @RequestMapping(value = "/ajax/",method = RequestMethod.POST)
    public OrdersBo ajax(HttpServletRequest request) {
        String zlkbmethod = request.getParameter("zlkbmethod");
        OrdersBo ordersBo = new OrdersBo();
        if (zlkbmethod != null && zlkbmethod.equals("orderid")) {
            String orderid = request.getParameter("orderid");
            List<TOrder> tOrder = tOrderService.queryAllOrderid(orderid);
            if(tOrder == null){
                ordersBo.setCode(1005);
                ordersBo.setMsg("订单不存在/当前IP与下单IP不符(最近1个月)");
            }else{
                ordersBo.setCode(1);
                ordersBo.setMsg("查询成功");
                ordersBo.setData(tOrder);
                PageInfo contents = new PageInfo(tOrder);
                ordersBo.setCount(contents.getTotal());
            }
        }else if(zlkbmethod != null && zlkbmethod.equals("contact")){
            String qq = request.getParameter("qq");
            String chapwd = request.getParameter("chapwd");
            List<TOrder> tOrders = tOrderService.queryByContact(qq, chapwd);
            if(tOrders == null){
                ordersBo.setCode(1005);
                ordersBo.setMsg("订单不存在/当前IP与下单IP不符(最近1个月)");
            }else{
                ordersBo.setCode(1);
                ordersBo.setMsg("查询成功");
                ordersBo.setData(tOrders);
                PageInfo contents = new PageInfo(tOrders);
                ordersBo.setCount(contents.getTotal());
            }

        }
        return ordersBo;
    }

    @ResponseBody
    @RequestMapping(value = "/kami",method = RequestMethod.POST)
    public KamiBo kami(HttpServletRequest request) {
        String orderid = request.getParameter("orderid");
        KamiBo kamiBo = new KamiBo();
        TOrder tOrder = tOrderService.queryByOrderid(orderid);
        String kami[]= tOrder.getKami().split(",");
        /*
        String[] kami = new String[5];
        kami[0] = tOrder.getKami();
        */
        kamiBo.setCode(1);
        kamiBo.setMsg("查询成功");
        kamiBo.setData(kami);
        return kamiBo;
    }
}
