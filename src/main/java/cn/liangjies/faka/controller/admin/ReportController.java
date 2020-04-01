package cn.liangjies.faka.controller.admin;

import cn.liangjies.faka.service.ConfigService;
import cn.liangjies.faka.service.TProductsTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/Admin/report")
public class ReportController {
    @Resource
    private ConfigService configService;

    @Resource
    private TProductsTypeService tProductsTypeService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("AdminUser", configService.getAdminEmail());
        model.addAttribute("config", configService.getConfig());
        model.addAttribute("version", configService.getVersion());
        return "admin/report/index";
    }


}
