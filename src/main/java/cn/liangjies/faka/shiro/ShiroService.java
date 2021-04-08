package cn.liangjies.faka.shiro;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Service;

@Service
public class ShiroService {

    @RequiresPermissions("read")
    public String read() {
        return "reading...";
    }

    @RequiresPermissions("write")
    public String write() {
        return "writting...";
    }
}