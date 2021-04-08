package cn.liangjies.faka.controller.admin;

import cn.liangjies.faka.entity.BO.TableBo;
import cn.liangjies.faka.entity.TProductsType;
import cn.liangjies.faka.service.ConfigService;
import cn.liangjies.faka.service.TProductsTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/Admin/productstype")
public class ProductstypeController {
    @Resource
    private ConfigService configService;

    @Resource
    private TProductsTypeService tProductsTypeService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("AdminUser", configService.getAdminEmail());
        model.addAttribute("config", configService.getConfig());
        model.addAttribute("version", configService.getVersion());
        model.addAttribute("productstypes", tProductsTypeService.queryActiveData());
        return "admin/productstype/index";
    }

    @GetMapping("/ajax")
    @ResponseBody
    public TableBo ajax(HttpServletRequest request) {
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));

        PageHelper.startPage(page, limit);
        List<TProductsType> list = this.tProductsTypeService.queryAllData();
        PageInfo contents = new PageInfo(list);

        TableBo tableBo = new TableBo();
        tableBo.setCount(contents.getTotal());
        tableBo.setData(list);
        return tableBo;
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("AdminUser", configService.getAdminEmail());
        model.addAttribute("config", configService.getConfig());
        model.addAttribute("version", configService.getVersion());
        return "admin/productstype/add";
    }

    @GetMapping("/edit/")
    public String edit(Model model,Integer id) {
        TProductsType tProductsType = tProductsTypeService.queryById(id);
        model.addAttribute("AdminUser", configService.getAdminEmail());
        model.addAttribute("config", configService.getConfig());
        model.addAttribute("version", configService.getVersion());
        model.addAttribute("item", tProductsType);

        return "admin/productstype/edit";
    }

    @PostMapping("/editajax")
    @ResponseBody
    public Object editajax(TProductsType tProductsType) {
        //设置返回消息
        HashMap<String, String> HashMapMsg = new HashMap<>();
        if(tProductsType.getId().equals(0) ||tProductsType.getId()==null){
            tProductsType.setIsdelete(0);
            tProductsType.setActive(1);

            try{
                tProductsTypeService.insert(tProductsType);
                HashMapMsg.put("code","1");
                return HashMapMsg;
            }catch (Exception e){
                HashMapMsg.put("code","0");
                HashMapMsg.put("msg","失败");
                e.printStackTrace();
            }
        }else{
            try{
                tProductsTypeService.update(tProductsType);
                HashMapMsg.put("code","1");
                return HashMapMsg;
            }catch (Exception e){
                HashMapMsg.put("code","0");
                HashMapMsg.put("msg","失败");
                e.printStackTrace();
            }
        }



        return HashMapMsg;
    }
}
