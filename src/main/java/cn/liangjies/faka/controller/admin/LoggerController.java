package cn.liangjies.faka.controller.admin;

import cn.liangjies.faka.entity.BO.TableBo;
import cn.liangjies.faka.entity.TAdminLoginLog;
import cn.liangjies.faka.service.ConfigService;
import cn.liangjies.faka.service.TAdminLoginLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/Admin/logger")
public class LoggerController {
    @Resource
    private ConfigService configService;

    @Resource
    private TAdminLoginLogService tAdminLoginLogService;
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("AdminUser",configService.getAdminEmail());
        model.addAttribute("config",configService.getConfig());
        model.addAttribute("version",configService.getVersion());
        return "admin/logger/index";
    }


    @GetMapping("/ajax")
    @ResponseBody
    public TableBo ajax(HttpServletRequest request) {
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));


        PageHelper.startPage(page,limit);
        List<TAdminLoginLog> list = this.tAdminLoginLogService.queryAll();
        PageInfo contents = new PageInfo(list);

        TableBo tableBo = new TableBo();
        tableBo.setCount(contents.getTotal());
        tableBo.setData(list);
        return tableBo;
    }
}
