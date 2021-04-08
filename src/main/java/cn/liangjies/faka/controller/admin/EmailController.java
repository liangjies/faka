package cn.liangjies.faka.controller.admin;

import cn.liangjies.faka.entity.BO.MsgBo;
import cn.liangjies.faka.entity.BO.TableBo;
import cn.liangjies.faka.entity.TEmail;
import cn.liangjies.faka.service.ConfigService;
import cn.liangjies.faka.service.TEmailService;
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
@RequestMapping("/Admin/email")
public class EmailController {
    @Resource
    private ConfigService configService;

    @Resource
    private TEmailService tEmailService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("AdminUser", configService.getAdminEmail());
        model.addAttribute("config", configService.getConfig());
        model.addAttribute("version", configService.getVersion());
        return "admin/email/index";
    }

    @GetMapping("/ajax")
    @ResponseBody
    public TableBo ajax(HttpServletRequest request) {
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));

        PageHelper.startPage(page, limit);
        List<TEmail> list = this.tEmailService.queryAll();
        PageInfo contents = new PageInfo(list);

        TableBo tableBo = new TableBo();
        tableBo.setCount(contents.getTotal());
        tableBo.setData(list);
        return tableBo;
    }

    @GetMapping("/add/")
    public String add(Model model) {
        model.addAttribute("AdminUser", configService.getAdminEmail());
        model.addAttribute("config", configService.getConfig());
        model.addAttribute("version", configService.getVersion());
        return "admin/email/add";
    }
    @GetMapping("/edit/")
    public String edit(Model model,HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));

        model.addAttribute("AdminUser", configService.getAdminEmail());
        model.addAttribute("config", configService.getConfig());
        model.addAttribute("version", configService.getVersion());
        model.addAttribute("email",tEmailService.queryById(id));
        System.out.println(tEmailService.queryById(id).getSmtpCrypto());
        return "admin/email/edit";
    }

    @RequestMapping(value = "/editajax", method = RequestMethod.POST)
    @ResponseBody
    public MsgBo editajax(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String method = request.getParameter("method");

        TEmail tEmail = new TEmail();
        tEmail.setIsactive(1);
        tEmail.setIsdelete(0);
        tEmail.setSendmail(request.getParameter("sendmail"));
        tEmail.setSendname(request.getParameter("sendname"));
        tEmail.setProtocol(request.getParameter("protocol"));
        tEmail.setHost(request.getParameter("host"));
        tEmail.setMailaddress(request.getParameter("mailaddress"));
        tEmail.setMailpassword(request.getParameter("mailpassword"));
        tEmail.setSmtpCrypto(Integer.parseInt(request.getParameter("smtp_crypto")));
        tEmail.setPort(request.getParameter("port"));
        tEmail.setId(Integer.parseInt(request.getParameter("id")));

        MsgBo msgBo = new MsgBo();
        if ("edit".equals(method) && id > 0) {
            tEmail.setIsactive(Integer.parseInt(request.getParameter("isactive")));
            tEmail.setIsdelete(0);
            if (tEmailService.update(tEmail)) {
                msgBo.setCode(1);
                msgBo.setMsg("更新成功");
            } else {
                msgBo.setCode(1003);
                msgBo.setMsg("更新失败");
            }
        } else {
            if (tEmailService.insert(tEmail)) {
                msgBo.setCode(1);
                msgBo.setMsg("新增成功");
            } else {
                msgBo.setCode(1003);
                msgBo.setMsg("新增失败");
            }
        }


        return msgBo;
    }

    @RequestMapping(value = "/delete/", method = RequestMethod.POST)
    @ResponseBody
    public MsgBo delete(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));

        MsgBo msgBo = new MsgBo();
        if(tEmailService.deleteById(id)){
            msgBo.setCode(1);
            msgBo.setMsg("删除成功");
        }else {
            msgBo.setCode(1003);
            msgBo.setMsg("删除失败");
        }
        return msgBo;
    }
}
