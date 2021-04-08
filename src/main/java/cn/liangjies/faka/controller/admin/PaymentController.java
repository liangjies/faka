package cn.liangjies.faka.controller.admin;

import cn.liangjies.faka.entity.BO.TableBo;
import cn.liangjies.faka.entity.TPayment;
import cn.liangjies.faka.service.ConfigService;
import cn.liangjies.faka.service.TPaymentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liangjies
 */
@Controller
@RequestMapping("/Admin/payment")
public class PaymentController {
    @Resource
    private ConfigService configService;

    @Resource
    private TPaymentService tPaymentService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("AdminUser", configService.getAdminEmail());
        model.addAttribute("config", configService.getConfig());
        model.addAttribute("version", configService.getVersion());
        return "admin/payment/index";
    }

    @GetMapping("ajax")
    @ResponseBody
    public TableBo list(Integer page,Integer limit) {
        PageHelper.startPage(page, limit);
        List<TPayment> list = this.tPaymentService.queryAllData();
        PageInfo contents = new PageInfo(list);

        TableBo tableBo = new TableBo();
        tableBo.setCount(contents.getTotal());
        tableBo.setData(list);
        return tableBo;
    }


}
