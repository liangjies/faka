package cn.liangjies.faka.controller.admin;

import cn.liangjies.faka.entity.TReport;
import cn.liangjies.faka.service.ConfigService;
import cn.liangjies.faka.service.TOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/Admin/report")
public class ReportController {
    @Resource
    private ConfigService configService;

    @Resource
    private TOrderService tOrderService;

    @GetMapping()
    public String index(Model model) throws ParseException {
        //获取当天开始和结束时间
        Date date = new Date();
        Calendar dateEnd = Calendar.getInstance();
        dateEnd.setTime(date);
        dateEnd.set(Calendar.HOUR_OF_DAY, 23);
        dateEnd.set(Calendar.MINUTE, 59);
        dateEnd.set(Calendar.SECOND, 59);
        
        //时间格式化
        int StartDateUnix = 0,EndDateUnix = 0;
        try {
            //当天开始时间
            SimpleDateFormat startformatter = new SimpleDateFormat("yyyy-MM-dd");
            String startdateString = startformatter.format(dateEnd.getTime());
            Date startdate = startformatter.parse(startdateString);
            StartDateUnix = (int)(startdate.getTime() / 1000);
            //当天结束时间
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String enddateString = formatter.format(dateEnd.getTime());
            Date enddate = formatter.parse(enddateString);
            System.out.println(enddate);
            EndDateUnix = (int)(enddate.getTime() / 1000);
        }catch (Exception e){
            e.printStackTrace();
        }

        /*当日统计*/
        //数据库查询
        TReport tReport = tOrderService.queryByTime(StartDateUnix, EndDateUnix);
        HashMap<String, BigDecimal> today_report = new HashMap<>();
        today_report.put("money", tReport.getShouru());
        today_report.put("total", BigDecimal.valueOf(tReport.getTotal()));



        model.addAttribute("AdminUser", configService.getAdminEmail());
        model.addAttribute("config", configService.getConfig());
        model.addAttribute("version", configService.getVersion());
        model.addAttribute("today_report", today_report);

        return "admin/report/index";
    }


}
