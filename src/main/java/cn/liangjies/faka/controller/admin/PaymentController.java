package cn.liangjies.faka.controller.admin;

import cn.liangjies.faka.service.ConfigService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author liangjies
 */
@Controller
@RequestMapping("/Admin/payment")
public class PaymentController {
    @Resource
    private ConfigService configService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("AdminUser", configService.getAdminEmail());
        model.addAttribute("config", configService.getConfig());
        model.addAttribute("version", configService.getVersion());
        return "admin/payment/index";
    }

}
