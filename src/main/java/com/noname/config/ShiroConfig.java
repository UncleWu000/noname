package com.noname.config;

import com.noname.auth.filter.TokenFormAuthenticationFilter;
import com.noname.mapper.PermissionMapper;
import com.noname.shiro.ShiroRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Autowired
    PermissionMapper permissionMapper;

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        System.out.println("shiro inject!!!");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        filters.put("authc", new TokenFormAuthenticationFilter());



        //设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //如果不设置会默认寻找Web工程下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/myLogin");

        //登陆成功之后跳转的页面
        shiroFilterFactoryBean.setSuccessUrl("/index");

        // 未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        Map<String, String> filterChainDefinitionMap = new  LinkedHashMap<>();

//        //配置不会被拦截的连接, 顺序判断
//        filterChainDefinitionMap.put("/static/**", "anon");
//        filterChainDefinitionMap.put("/ajaxLogin", "anon");
//
//        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
//        filterChainDefinitionMap.put("/logout", "logout");
//
//        filterChainDefinitionMap.put("/add", "perms[权限添加]");

        // <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
//        Permission permission = permissionMapper.selectByPrimaryKey(1);
//        System.out.println(permission.getUrl());
//        List<Permission> permissions = permissionMapper.selectAll();
//        permissions.forEach(p-> System.out.println(p.getUrl()+":"+p.getName()));
        filterChainDefinitionMap.put("/article/list/**",  "anon");
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        System.out.println("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;

    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    /**
     * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
     *
     * @return
     */
    @Bean
    public ShiroRealm myShiroRealm() {
        ShiroRealm myShiroRealm = new ShiroRealm();
        return myShiroRealm;
    }




}
