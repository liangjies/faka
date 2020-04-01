package cn.liangjies.faka.controller.admin;

import cn.liangjies.faka.entity.BO.MsgBo;
import cn.liangjies.faka.service.ConfigService;
import cn.liangjies.faka.service.TAdminUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/Admin/profiles")
public class ProfilesController {
    @Resource
    private TAdminUserService tAdminUserService;

    @Resource
    private ConfigService configService;


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("AdminUser",configService.getAdminEmail());
        model.addAttribute("config",configService.getConfig());
        model.addAttribute("version",configService.getVersion());
        return "admin/profiles/index";
    }

    @GetMapping(value = {"/password/"})
    public String password(Model model) {
        model.addAttribute("AdminUser",configService.getAdminEmail());
        model.addAttribute("config",configService.getConfig());
        model.addAttribute("version",configService.getVersion());
        return "admin/profiles/password";
    }

    @RequestMapping(value = "/passwordajax",method = RequestMethod.POST)
    @ResponseBody
    public MsgBo edit_password(HttpServletRequest request) {
        String oldpassword = request.getParameter("oldpassword");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");

        MsgBo msgBo = new MsgBo();
        if(password.length()<6){
            msgBo.setCode(1002);
            msgBo.setMsg("密码过于简单,密码至少6位");
        }else if(oldpassword==password||oldpassword==repassword){
            msgBo.setCode(1001);
            msgBo.setMsg("新旧密码不能相同");
        }else if(!tAdminUserService.validPass(DigestUtils.md5DigestAsHex(oldpassword.getBytes()))){
            msgBo.setCode(1003);
            msgBo.setMsg("原始密码不正确");
        }else if(tAdminUserService.updatePass(DigestUtils.md5DigestAsHex(password.getBytes()))){
            msgBo.setCode(1);
            msgBo.setMsg("修改密码成功");
        }else {
            msgBo.setCode(1001);
            msgBo.setMsg("数据更新异常");
        }

        return msgBo;
    }

}
