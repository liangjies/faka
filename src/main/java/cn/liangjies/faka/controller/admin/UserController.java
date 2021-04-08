package cn.liangjies.faka.controller.admin;

import cn.liangjies.faka.entity.BO.MsgBo;
import cn.liangjies.faka.entity.BO.TableBo;
import cn.liangjies.faka.entity.TUser;
import cn.liangjies.faka.service.ConfigService;
import cn.liangjies.faka.service.TUserService;
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
import java.util.List;

@Controller
@RequestMapping(value = "/Admin/user")
public class UserController {
    @Resource
    private ConfigService configService;

    @Resource
    private TUserService tUserService;
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("AdminUser", configService.getAdminEmail());
        model.addAttribute("config", configService.getConfig());
        model.addAttribute("version", configService.getVersion());
        return "admin/user/index";
    }

    @GetMapping("/ajax")
    @ResponseBody
    public TableBo ajax(HttpServletRequest request) {
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));

        PageHelper.startPage(page, limit);
        List<TUser> list = this.tUserService.queryAllData();
        PageInfo contents = new PageInfo(list);

        TableBo tableBo = new TableBo();
        tableBo.setCount(contents.getTotal());
        tableBo.setData(list);
        return tableBo;
    }

    @RequestMapping(value = "/delete/", method = RequestMethod.POST)
    @ResponseBody
    public MsgBo delete(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));

        MsgBo msgBo = new MsgBo();
        if(tUserService.deleteById(id)){
            msgBo.setCode(1);
            msgBo.setMsg("删除成功");
        }else {
            msgBo.setCode(1003);
            msgBo.setMsg("删除失败");
        }
        return msgBo;
    }
}
