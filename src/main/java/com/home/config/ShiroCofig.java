package com.home.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;


import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Mr.X
 **/

@Configuration
public class ShiroCofig {

    @Bean
    public ShiroFilterFactoryBean shirFilter() {
        System.out.println("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        // 设置login URL
        shiroFilterFactoryBean.setLoginUrl("/login/index");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 未授权的页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        // 拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 设置登录的URL为匿名访问，因为一开始没有用户验证
        filterChainDefinitionMap.put("/login/index", "anon");
        filterChainDefinitionMap.put("/use/put", "anon");
        filterChainDefinitionMap.put("/use/post", "anon");
        filterChainDefinitionMap.put("/index", "authc");
        // 退出系统的过滤器
        filterChainDefinitionMap.put("/Start/loginout.do", "logout");
        // 现在资源的角色
//        filterChainDefinitionMap.put("/user/main/index", "roles[admin]");
        // filterChainDefinitionMap.put("/user.html", "roles[user]");
        // 固定格式
//        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1024);// 散列的次数，比如散列两次，相当于md5(md5(""));
        return hashedCredentialsMatcher;
    }

    @Bean
    public MyRealm myShiroRealm() {
        MyRealm myShiroRealm = new MyRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }
    @Bean
    public DefaultSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myShiroRealm());
        manager.setRememberMeManager(rememberMeManager());
        manager.setSessionManager(sessionManager());
        return manager;
    }



    /**
     * 注册shiro方言，让thymeleaf支持shiro标签
     */
    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

    /**
     * 自动代理类，支持Shiro的注解
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator =
                new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启Shiro的注解
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
                new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * --记住我之rememberMeCookie
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //如果httyOnly设置为true，则客户端不会暴露给客户端脚本代码，
        //使用HttpOnly cookie有助于减少某些类型的跨站点脚本攻击；
        simpleCookie.setHttpOnly(true);
        //记住我cookie生效时间,单位是秒
        simpleCookie.setMaxAge(10 * 24 * 60 * 60);
        return simpleCookie;
    }

    /**
     * --记住我之cookie管理器
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        byte[] cipherKey = Base64.decode("wGiHplamyXlVB11UXWol8g==");
        cookieRememberMeManager.setCipherService(new AesCipherService());
        cookieRememberMeManager.setCipherKey(cipherKey);
        return cookieRememberMeManager;
    }

    /**
     * sessionCookie
     */
    @Bean
    public SimpleCookie sessionCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("shiro.sesssion");
        simpleCookie.setPath("/");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(1 * 24 * 60 * 60);
        return simpleCookie;
    }

    /**
     * 1、session 管理，去掉重定向后Url追加SESSIONID
     * 2、shiro默认Cookie名称是JSESSIONID，与servlet(jetty, tomcat等默认JSESSIONID)冲突，
     * --我们需要为shiro指定一个不同名称的Session id，否则抛出UnknownSessionException:
     * There is no session with id异常
     * 当我们使用热部署重启应用时，已有的Session消失，shiro跳转到登录页面，也会抛出此异常
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setSessionIdCookie(sessionCookie());
        return sessionManager;
    }

}