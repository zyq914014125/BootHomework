package com.home.Interceptor;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 * @author Mr.X
 **/
@Component
public class RequestViewInterceptor implements HandlerInterceptor {


    /**
     * @Description preHandle ，after 这里重写省去，原接口已default 自动调用接口的
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    @SuppressWarnings("all")
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            //没有视图，返回包含重定向统统跳过拦截器
        if(modelAndView==null ||modelAndView.getViewName().startsWith("redirect"))
            return;
        //请求地址获取(url)
        String path=request.getServletPath();
        //获取key为 page的modelmap
       String page=(String)modelAndView.getModelMap().get("page");
       //page为空那么，拦截器自动给modelmap（"page",value)
       if(StringUtils.isBlank(page)){
           //(默认，url请求路径，与html文件所在路径一致，url以/开头，则去除即为文件路径）
           if(path.startsWith("/")){
               //去 “/”
               path=path.substring(1);
           }
           //自动modelmap
           modelAndView.getModelMap().addAttribute("page",path);
       }
       //手动给了modelmap（"page",value),拦截器没用，自动路过
        HandlerInterceptor.super.preHandle(request, response, handler);
    }


}
