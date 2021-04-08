package cn.liangjies.faka.controller.admin;

import cn.liangjies.faka.entity.BO.CardType;
import cn.liangjies.faka.entity.BO.TableBo;
import cn.liangjies.faka.entity.Dto.CardSearchDto;
import cn.liangjies.faka.entity.TProducts;
import cn.liangjies.faka.entity.TProductsCard;
import cn.liangjies.faka.service.ConfigService;
import cn.liangjies.faka.service.TProductsCardService;
import cn.liangjies.faka.service.TProductsService;
import cn.liangjies.faka.service.TProductsTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/Admin/productscard")
public class ProductscardController {
    @Resource
    private ConfigService configService;

    @Resource
    private TProductsCardService tProductsCardService;

    @Resource
    private TProductsTypeService tProductsTypeService;

    @Resource
    private TProductsService tProductsService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("AdminUser", configService.getAdminEmail());
        model.addAttribute("config", configService.getConfig());
        model.addAttribute("version", configService.getVersion());
        model.addAttribute("products", tProductsService.queryActiveData());

        return "admin/productscard/index";
    }

    @GetMapping("/ajax")
    @ResponseBody
    public TableBo ajax(CardSearchDto cardSearchDto) {
        System.out.println(cardSearchDto.getPid());
        PageHelper.startPage(cardSearchDto.getPage(), cardSearchDto.getLimit());
        List<CardType> list = this.tProductsCardService.queryCardAndType(cardSearchDto);
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
        model.addAttribute("productstypes", tProductsTypeService.queryActiveData());
        return "admin/productscard/add";
    }

    @GetMapping("/addplus")
    public String addplus(Model model) {
        model.addAttribute("AdminUser", configService.getAdminEmail());
        model.addAttribute("config", configService.getConfig());
        model.addAttribute("version", configService.getVersion());
        model.addAttribute("productstypes", tProductsTypeService.queryActiveData());
        return "admin/productscard/addplus";
    }

    @GetMapping("/import")
    public String import_(Model model) {
        model.addAttribute("AdminUser", configService.getAdminEmail());
        model.addAttribute("config", configService.getConfig());
        model.addAttribute("version", configService.getVersion());
        model.addAttribute("productstypes", tProductsTypeService.queryActiveData());
        return "admin/productscard/import";
    }

    @GetMapping("/download")
    public String download(Model model) {
        model.addAttribute("AdminUser", configService.getAdminEmail());
        model.addAttribute("config", configService.getConfig());
        model.addAttribute("version", configService.getVersion());
        model.addAttribute("productstypes", tProductsTypeService.queryActiveData());
        return "admin/productscard/addplus";
    }

    @RequestMapping("/addajax")
    @ResponseBody
    public HashMap addajax(TProductsCard tProductsCard) {
        HashMap<String, String> HashMapMsg = new HashMap<>();
        String[] cards=tProductsCard.getCard().split("\n");
        Boolean status = Boolean.TRUE;

        for(int i=0;i<cards.length;i++){
            try{
                tProductsCard.setAddtime((int) (System.currentTimeMillis()/1000));
                tProductsCard.setActive(0);
                tProductsCard.setIsdelete(0);
                tProductsCard.setCard(cards[i]);
                tProductsCardService.insert(tProductsCard);

                TProducts tProducts = tProductsService.queryById(tProductsCard.getPid());
                tProducts.setQty(tProducts.getQty() + 1);
                tProductsService.update(tProducts);
            }catch (Exception e){
                status = Boolean.FALSE;
                e.printStackTrace();
            }
        }
        if(status){
             HashMapMsg.put("code","1");
        }else {
            HashMapMsg.put("code","0");
            HashMapMsg.put("msg","失败");
        }

        return HashMapMsg;
    }

    @RequestMapping("/delete/")
    @ResponseBody
    public HashMap delete(Integer id) {
        HashMap<String, String> HashMapMsg = new HashMap<>();

        try{
            boolean b = tProductsCardService.deleteById(id);
            if(b){
                HashMapMsg.put("code","1");
                return HashMapMsg;
            }else {
                HashMapMsg.put("code","0");
                HashMapMsg.put("msg","失败");
                return HashMapMsg;
            }

        }catch (Exception e){
            HashMapMsg.put("code","0");
            HashMapMsg.put("msg","失败");
            e.printStackTrace();
        }
        return HashMapMsg;
    }
}
