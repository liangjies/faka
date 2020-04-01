package cn.liangjies.faka.controller;

import cn.liangjies.faka.service.ConfigService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/about")
public class AboutController {
    /**
     * 服务对象
     */

    @Resource
    private ConfigService configService;

    @GetMapping()
    public String about(Model model) {
        model.addAttribute("AdminUser", configService.getAdminEmail());
        model.addAttribute("config", configService.getConfig());
        model.addAttribute("version", configService.getVersion());
        return "about/index";
    }
}
