package cn.liangjies.faka.controller;

import cn.liangjies.faka.service.ConfigService;
import cn.liangjies.faka.service.TProductsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
public class IndexController {
    /**
     * 服务对象
     */
    @Resource
    private ConfigService configService;

    @Resource
    private TProductsService tProductsService;

    @RequestMapping(value = {"/","/product"}, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("AdminUser", configService.getAdminEmail());
        model.addAttribute("config", configService.getConfig());
        model.addAttribute("version", configService.getVersion());
        return "hyacinth/index/index";
    }

}
