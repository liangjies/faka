package cn.liangjies.faka.controller;

import cn.liangjies.faka.shiro.ShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShiroController {
    @Autowired
    private ShiroService shiroSampleService;

    @GetMapping("/login")
    public void login(String username, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, DigestUtils.md5DigestAsHex(password.getBytes()));
        token.setRememberMe(true);
        Subject currentUser = SecurityUtils.getSubject();
        try
        {
            currentUser.login(token);
            System.out.print("成功");
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            System.out.print(msg);
        }
        //currentUser.login(token);
    }

    @GetMapping("/logout")
    public void logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
    }

    @GetMapping("/read")
    public String read() {
        return this.shiroSampleService.read();
    }

    @GetMapping("/write")
    public String write() {
        return this.shiroSampleService.write();
    }
}
