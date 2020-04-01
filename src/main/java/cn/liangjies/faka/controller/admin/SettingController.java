package cn.liangjies.faka.controller.admin;


import cn.liangjies.faka.entity.BO.MsgBo;
import cn.liangjies.faka.entity.BO.TableBo;
import cn.liangjies.faka.entity.TConfig;
import cn.liangjies.faka.service.ConfigService;
import cn.liangjies.faka.service.TConfigService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/Admin/setting")
public class SettingController {
    @Resource
    private ConfigService configService;

    @Resource
    private TConfigService tConfigService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("AdminUser", configService.getAdminEmail());
        model.addAttribute("config", configService.getConfig());
        model.addAttribute("version", configService.getVersion());
        return "admin/setting/index";
    }

    @GetMapping("/ajax")
    @ResponseBody
    public TableBo ajax(HttpServletRequest request) {
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));

        PageHelper.startPage(page, limit);
        List<TConfig> list = this.tConfigService.queryAll();
        PageInfo contents = new PageInfo(list);

        TableBo tableBo = new TableBo();
        tableBo.setCount(contents.getTotal());
        tableBo.setData(list);
        return tableBo;
    }

    @GetMapping("/edit/")
    public String edit(Model model,HttpServletRequest request){
        model.addAttribute("AdminUser", configService.getAdminEmail());
        model.addAttribute("config", configService.getConfig());
        model.addAttribute("version", configService.getVersion());

        int id = 1;
        id = Integer.parseInt(request.getParameter("id"));
        TConfig tConfig = this.tConfigService.queryById(id);
        model.addAttribute("item",tConfig);

        String fileUrl = "src/main/resources/templates/admin/setting/tpl/"+tConfig.getName()+".html";
        File file = new File(fileUrl);
        System.out.println(fileUrl);
        if (file.exists()) {
            return "admin/setting/tpl/"+tConfig.getName()+"";
        }else{
            return "admin/setting/edit";
        }
    }

    @RequestMapping(value = "/editajax", method = RequestMethod.POST)
    @ResponseBody
    public MsgBo editajax(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String value = request.getParameter("value");

        TConfig tConfig = new TConfig();
        tConfig.setId(id);
        tConfig.setName(name);
        tConfig.setValue(value);

        MsgBo msgBo = new MsgBo();
        if(this.tConfigService.update(tConfig)){
            msgBo.setCode(1);
            msgBo.setMsg("更新成功");
        }else {
            msgBo.setCode(1003);
            msgBo.setMsg("更新失败");
        }
        return msgBo;
    }
}