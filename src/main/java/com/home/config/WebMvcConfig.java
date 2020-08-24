package com.home.config;

import com.home.Interceptor.RequestViewInterceptor;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.home.Fliter.RequestParamFilter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Mr.X
 **/
@Configuration
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private RequestViewInterceptor requestViewInterceptor;
    @Autowired
    private ResourceConfig resourceConfig;
    @Bean
    public Connector connector(){
        Connector connector=new Connector();
        connector.setPort(80);
        connector.setScheme("http");
        return connector;
    }
    @Bean
    public ServletWebServerFactory webServerFactory(){
        TomcatServletWebServerFactory tomcatServletWebServerFactory=new TomcatServletWebServerFactory();
        tomcatServletWebServerFactory.addAdditionalTomcatConnectors(connector());
        return tomcatServletWebServerFactory;
    }
//    //过滤器注册
//@Bean
//    public FilterRegistrationBean<RequestParamFilter> register() {
//      FilterRegistrationBean<RequestParamFilter> register =
//            new FilterRegistrationBean<RequestParamFilter>();
//       register.setFilter(new RequestParamFilter());
//    return register;
//}
    //拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestViewInterceptor).addPathPatterns("/menu/**");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //获取本机mac
        String osName = System.getProperty("os.name");
        if (osName.startsWith("win")) {
            registry.addResourceHandler(resourceConfig.getRelativePathPattern())
                    .addResourceLocations(ResourceUtils.FILE_URL_PREFIX +
                            resourceConfig.getLocationPathForWindows());
        } else {
            registry.addResourceHandler(resourceConfig.getRelativePathPattern())
                    .addResourceLocations(ResourceUtils.FILE_URL_PREFIX +
                            resourceConfig.getLocationPathForLinux());
        }
    }
}

