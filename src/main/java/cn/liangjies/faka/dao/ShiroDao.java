package cn.liangjies.faka.dao;

import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class ShiroDao {
    /**
     * 服务对象
     */

    public Set<String> getRolesByUsername(String username) {
//        TAdminUser tAdminUser = tAdminUserService.queryByEmail(username);

        Set<String> roles = new HashSet<>();
        roles.add("admin");
//        switch (username) {
//            case "zhangsan":
//                roles.add("admin");
//                break;
//            case "lisi":
//                roles.add("guest");
//                break;
//        }
        return roles;
    }

    public Set<String> getPermissionsByRole(String role) {
        Set<String> permissions = new HashSet<>();
        switch (role) {
            case "admin":
                permissions.add("admin");
                break;
            case "user":
                permissions.add("user");
                break;
        }
        return permissions;
    }

    public String getPasswordByUsername(String username) {
        switch (username) {
            case "zhangsan":
                return "zhangsan";
            case "lisi":
                return "lisi";
        }
        return null;
//        TAdminUser tAdminUser = tAdminUserService.queryByEmail(username);
//        return tAdminUser.getPassword();
    }
}
