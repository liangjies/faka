package cn.liangjies.faka.controller.admin;

import cn.liangjies.faka.entity.BO.TableBo;
import cn.liangjies.faka.entity.Dto.GetListDto;
import cn.liangjies.faka.entity.TProducts;
import cn.liangjies.faka.service.ConfigService;
import cn.liangjies.faka.service.TProductsService;
import cn.liangjies.faka.service.TProductsTypeService;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/Admin/products")
public class ProductsController {
    @Resource
    private ConfigService configService;

    @Resource
    private TProductsService tProductsService;

    @Resource
    private TProductsTypeService tProductsTypeService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("AdminUser", configService.getAdminEmail());
        model.addAttribute("config", configService.getConfig());
        model.addAttribute("version", configService.getVersion());
        return "admin/products/index";
    }

    @GetMapping("/ajax")
    @ResponseBody
    public TableBo ajax(HttpServletRequest request) {
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));

        PageHelper.startPage(page, limit);
        List<TProducts> list = this.tProductsService.queryAllData();
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
        model.addAttribute("productstypes", tProductsTypeService.queryActiveData());

        return "admin/products/add";
    }

    @PostMapping("/getlistbytid")
    @ResponseBody
    public GetListDto getlistbytid(@Param("tid") Integer tid) {
        System.out.println(tid);
        List<TProducts> tProducts = tProductsService.queryByTid(tid);

        GetListDto getListDto = new GetListDto();
        getListDto.setLength(tProducts.size());
        getListDto.setCode(1);
        getListDto.setProducts(tProducts);

        return getListDto;
    }

    @GetMapping("/edit/")
    public String edit(Model model,Integer id) {
        TProducts tProducts = tProductsService.queryById(id);
        model.addAttribute("AdminUser", configService.getAdminEmail());
        model.addAttribute("config", configService.getConfig());
        model.addAttribute("version", configService.getVersion());
        model.addAttribute("product", tProducts);
        model.addAttribute("productstypes", tProductsTypeService.queryActiveData());
        return "admin/products/edit";
    }

    @GetMapping("/imgurl/")
    public String imgurl(Model model,Integer id) {
        TProducts tProducts = tProductsService.queryById(id);
        model.addAttribute("AdminUser", configService.getAdminEmail());
        model.addAttribute("config", configService.getConfig());
        model.addAttribute("version", configService.getVersion());
        model.addAttribute("product", tProducts);

        return "admin/products/imgurl";
    }


    @PostMapping("/imgurlajax")
    @ResponseBody
    public Object imgurlajax(@RequestParam(value = "file") MultipartFile file, @Param("pid") Integer pid) {
        RestTemplate restTemplate = new RestTemplate();

        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data");
        headers.setContentType(type);
        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("image",file.getResource());

        //请求地址
        String url = "https://image.kieng.cn/upload.html?type=ali";

        //设置返回消息
        HashMap<String, String> HashMapMsg = new HashMap<>();


        try{
            // 用HttpEntity封装整个请求报文
            HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(parts, headers);
            String responseBean = restTemplate.postForObject(url, files, String.class);

            //把接送字符串解析成json对象进行操作
            JSONObject json  = JSONObject.parseObject(responseBean);
            System.out.println(json.getString("code"));
            if(Integer.parseInt(json.getString("code"))!=200){
                HashMapMsg.put("code","0");
                HashMapMsg.put("msg","上传失败");
                return HashMapMsg;
            }

            JSONObject data = json.getJSONObject("data");
            //插入数据库
            TProducts tProducts = new TProducts();
            tProducts.setId(pid);
            tProducts.setImgurl(data.getString("url"));

            tProductsService.update(tProducts);
            HashMapMsg.put("code","1");
            return HashMapMsg;
        }catch (Exception e){
            HashMapMsg.put("code","0");
            HashMapMsg.put("msg","错误");
            e.printStackTrace();
        }


        return HashMapMsg;
    }


    @PostMapping("/editajax")
    @ResponseBody
    public Object editajax(TProducts tProducts) {
        //设置返回消息
        HashMap<String, String> HashMapMsg = new HashMap<>();
        if(tProducts.getId().equals("") ||tProducts.getId()==null){
            tProducts.setAddtime((int) (System.currentTimeMillis()/1000));
            tProducts.setIsdelete(0);
            tProducts.setIszhekou(0);
            tProducts.setImgurl("");

            try{
                tProductsService.insert(tProducts);
                HashMapMsg.put("code","1");
                return HashMapMsg;
            }catch (Exception e){
                HashMapMsg.put("code","0");
                HashMapMsg.put("msg","失败");
                e.printStackTrace();
            }
        }else{
            try{
                tProductsService.update(tProducts);
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

    @RequestMapping("/delete/")
    @ResponseBody
    public HashMap delete(Integer id) {
        HashMap<String, String> HashMapMsg = new HashMap<>();

        try{
            boolean b = tProductsService.deleteById(id);
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
