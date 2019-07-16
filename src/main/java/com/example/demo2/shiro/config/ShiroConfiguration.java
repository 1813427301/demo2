package com.example.demo2.shiro.config;

import com.example.demo2.domian.User;
import com.example.demo2.service.UserService;
import com.example.demo2.service.impl.UserServiceImpl;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Configuration
public class ShiroConfiguration {
    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * <p>
     * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     */
    @Autowired(required = false)
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) throws AuthenticationException {
        System.out.println("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //拦截器.
        Map<String, String> filterMap = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 顺序判断
        filterMap.put("/Json/**", "anon");
        filterMap.put("/", "anon");
        filterMap.put("/manage/login", "anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
      //  filterMap.put("/logout", "logout");
       // filterMap.put("/adminOut","logout");
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->


             /*   filterMap.put("/studentJson/**", "authc,roles[0]");
                filterMap.put("/student_form/**", "authc,roles[0]");
                filterMap.put("/student/**", "authc,roles[0]");

                filterMap.put("/teacher/**", "authc,roles[1]");
                filterMap.put("/teacher_form/**", "authc,roles[1]");
                filterMap.put("/teacherJson/**", "authc,roles[1]");

                filterMap.put("/manage/**", "authc,roles[2]");
                filterMap.put("/afterss/**", "authc,roles[2]");

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面*/
        shiroFilterFactoryBean.setLoginUrl("/manage/login");
        // 登录成功后要跳转的链接
       // shiroFilterFactoryBean.setSuccessUrl("/manage/index");
        //shiroFilterFactoryBean.setSuccessUrl("/user/index");
        //无授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/error/500");
        logger.info("拦截器链：" + filterMap);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(myShiroRealm());
        // 自定义缓存实现 使用redis
        //securityManager.setCacheManager(cacheManager());
        // 自定义session管理 使用redis
        //securityManager.setSessionManager(sessionManager());
        //注入记住我管理器;
        return securityManager;
    }

    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());// 配置 加密 （在加密后，不配置的话会导致登陆密码失败）
        System.out.println("myShiroRealm 注入成功");
        return myShiroRealm;
    }

    /*加盐加密设置*/
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5"); // 使用md5 算法进行加密
        hashedCredentialsMatcher.setHashIterations(1024); // 设置散列次数： 意为加密几次
        System.out.println("hashedCredentialsMatcher");
        return hashedCredentialsMatcher;
    }

    /*生命周期*/
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    //开启shiro aop注解支持
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
//        System.out.println("开启了Shiro注解支持");
//        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//        return authorizationAttributeSourceAdvisor;
//    }

    @Bean(name="simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.setProperty("UnauthorizedException", "/error/403");  //捕捉权限异常跳转403页面
        exceptionResolver.setExceptionMappings(mappings);  // None by default
        exceptionResolver.setDefaultErrorView("error");    // No default
        exceptionResolver.setExceptionAttribute("ex");     // Default is "exception" //页面上获取异常信息变量名
        return exceptionResolver;
    }


}
