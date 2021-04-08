package cn.liangjies.faka.controller.admin;

import cn.liangjies.faka.entity.BO.AuthBo;
import cn.liangjies.faka.entity.TAdminLoginLog;
import cn.liangjies.faka.service.TAdminLoginLogService;
import cn.liangjies.faka.service.TConfigService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: liangjies
 * @create: 2020-03-22 13:07
 */
@Controller
@RequestMapping(value = "/Admin")
public class AuthController {
    @Resource
    TConfigService tConfigService;

    @Resource
    TAdminLoginLogService tAdminLoginLogService;
    @GetMapping(value = {"/login"})
    public String admin_index(Model model) {
        return "admin/login/index";
    }

    /**
     * 获取客户端ip地址
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) {
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress="";
        }
        // ipAddress = this.getRequest().getRemoteAddr();

        return ipAddress;
    }

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public AuthBo admin_login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        AuthBo authBo = new AuthBo();

        UsernamePasswordToken token = new UsernamePasswordToken(username, DigestUtils.md5DigestAsHex(password.getBytes()));
        token.setRememberMe(true);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
            authBo.setCode(200);
            /**
             * 记录登录日志
             */
            int unixtime = (int) (System.currentTimeMillis()/1000);
            TAdminLoginLog tAdminLoginLog = new TAdminLoginLog();
            tAdminLoginLog.setIp(getIpAddr(request));
            tAdminLoginLog.setAddtime(unixtime);
            tAdminLoginLog.setAdminid(1);
            tAdminLoginLogService.insert(tAdminLoginLog);
            authBo.setMsg("登录成功");
            //System.out.print("成功");
        } catch (AuthenticationException e) {
            authBo.setCode(0);
            authBo.setMsg("用户名或密码错误");
        }
        return authBo;
    }

    @GetMapping(value = {""})
    @RequiresPermissions("admin")
    public String admin_i(Model model) {
        List<Map> listOfMap = tConfigService.queryConfig();
        Map<Object,Object> config = new HashMap<Object,Object>();
        for(int i = 0; i < listOfMap.size(); i++) {
            Object key = (Object) listOfMap.get(i).get("name");
            Object value = (Object)listOfMap.get(i).get("value");
            config.put(key, value);
        }

        model.addAttribute("config",config);
        model.addAttribute("version","0.0.1");
        return "admin/index/index";
    }

}
