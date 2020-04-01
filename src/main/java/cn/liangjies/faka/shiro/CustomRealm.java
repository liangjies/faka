package cn.liangjies.faka.shiro;

import cn.liangjies.faka.dao.ShiroDao;
import cn.liangjies.faka.dao.TAdminUserDao;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private ShiroDao shiroDao;

    @Resource
    private TAdminUserDao tAdminUserDao;

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = tAdminUserDao.queryByEmail(username).getPassword();
        return new SimpleAuthenticationInfo(username, password, getName());
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) super.getAvailablePrincipal(principalCollection);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = shiroDao.getRolesByUsername(username);
        authorizationInfo.setRoles(roles);
        roles.forEach(role -> {
            Set<String> permissions = this.shiroDao.getPermissionsByRole(role);
            System.out.println(permissions);
            authorizationInfo.addStringPermissions(permissions);
        });
        return authorizationInfo;
    }
}
