package cn.liangjies.faka.config;

import cn.liangjies.faka.shiro.CustomRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class ShiroConfig {
    /**
     * 自定义身份认证
     * 必须写这个类，并加上 @Bean 注解，目的是注入 CustomRealm，
     * 否则会影响 CustomRealm类 中其他类的依赖注入
     *
     * @return
     */
    @Bean(name = "customRealm")
    public CustomRealm customRealm() {
        return new CustomRealm();
    }

    /**
     * 注入 securityManager安全管理器
     * DefaultWebSecurityManager
     * @param customRealm
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(CustomRealm customRealm) {
        DefaultWebSecurityManager  securityManager = new DefaultWebSecurityManager ();
        securityManager.setRealm(customRealm);
        return securityManager;
    }

    /**
     * Spring的Bean post处理器，它自动调用实现{@link org.apache.shiro.util.Initializable}的 init（）和{@link org.apache.shiro.util.Destroyable}接口的 destroy（）方法,。
     * 这个后处理器可以直接在Spring中配置Shiro bean即可。用户不必担心是否必须指定init-method和destroy-method bean属性。
     * 注意：此后处理器无法确定是否已经调用init（）或 destroy（），因此如果在applicationContext中，也不要手动或通过Spring的bean属性调用init-method 或 destroy-method 这些方法。
     *
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 授权未通过时(403)错误处理,没有这个不会跳转到403页面
     * 没有授权的处理
     * 配合注解的使用，使用代码配置ShiroFilter的，直接是setUnauthorizedUrl
     * 使用该方法可以配置多个异常匹配
     * @return
     */
    @Bean
    public SimpleMappingExceptionResolver getSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.setProperty("org.apache.shiro.authz.UnauthorizedException", "/403");
        mappings.setProperty("org.apache.shiro.authz.AuthorizationException", "/403");
        simpleMappingExceptionResolver.setExceptionMappings(mappings);
        return simpleMappingExceptionResolver;
    }

    /**
     * shiroFilter
     * 不使用注解的拦截器写法
     * 没有权限之后的跳转必须是登录以后才能识别的
     *     这些名为anon,authc,roles,perms等的过滤器，实际上是一些{@link javax.servlet.Filter}接口的实现，它们都位于org.apache.shiro.web.filter包中。
     * 通过{@link org.apache.shiro.web.filter.mgt.DefaultFilter} 知道了那些拦截器的命名
     *         anon对应{@link org.apache.shiro.web.filter.authc.AnonymousFilter}
     *         authc对应{@link org.apache.shiro.web.filter.authc.FormAuthenticationFilter}
     *         roles对应{@link org.apache.shiro.web.filter.authz.RolesAuthorizationFilter}
     *         perms对应{@link org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter}
     * @param securityManager
     * @return
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shirFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
        shiroFilterFactoryBean.setLoginUrl("/Admin/login");
        // 设置无权限时跳转的 url;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/Admin");
        // 设置拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //开放接口
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/index", "anon");
        filterChainDefinitionMap.put("/", "anon");
        //filterChainDefinitionMap.put("/product/**", "anon");

        //退出登录
        filterChainDefinitionMap.put("/Admin/logout/", "logout");

        //用户，需要角色权限 “user”
        //filterChainDefinitionMap.put("/Admin", "perms[admin]");
        filterChainDefinitionMap.put("/insert", "perms[user:insert]");
        filterChainDefinitionMap.put("/update", "perms[user:update]");
        filterChainDefinitionMap.put("/delete", "perms[user:delete]");
        filterChainDefinitionMap.put("/select", "perms[user:select]");

        //其余接口一律拦截
        //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
        //过滤链定义，从上向下顺序执行，一般将/**放在最为下边
        filterChainDefinitionMap.put("/Admin", "authc");
        filterChainDefinitionMap.put("/admin", "authc");
        //filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        System.out.println("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }
}