package cn.liangjies.faka.controller;

import cn.liangjies.faka.entity.BO.TableBo;
import cn.liangjies.faka.entity.TProducts;
import cn.liangjies.faka.service.ConfigService;
import cn.liangjies.faka.service.TProductsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * (TProducts)表控制层
 *
 * @author liangjies
 * @since 2020-03-21 15:23:23
 */
@Controller
@RequestMapping("product")
public class IndexProductsController {
    /**
     * 服务对象
     */
    @Resource
    private TProductsService tProductsService;

    @Resource
    private ConfigService configService;

    @GetMapping("/get")
    @ResponseBody
    public TableBo index(HttpServletRequest request) {
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));
        int tid = Integer.parseInt(request.getParameter("tid"));

        PageHelper.startPage(page,limit);
        List<TProducts> list = this.tProductsService.queryAllData();
        PageInfo contents = new PageInfo(list);

        TableBo tableBo = new TableBo();
        tableBo.setCount(contents.getTotal());
        tableBo.setData(list);
        return tableBo;
    }

    @RequestMapping(value = "/{id}.html",method= RequestMethod.GET)
    public String product(Model model, @PathVariable String id) {

        //System.out.println(Integer.parseInt(id));
        TProducts tProducts = tProductsService.queryById(Integer.parseInt(id));
        model.addAttribute("AdminUser", configService.getAdminEmail());
        model.addAttribute("config", configService.getConfig());
        model.addAttribute("version", configService.getVersion());
        model.addAttribute("product",tProducts);
        return "hyacinth/detail/index";
    }

}