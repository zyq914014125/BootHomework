package com.home.Fliter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

/**
 * 过滤器
 * @author Mr.X
 **/
@WebFilter(filterName = "requestParamaFilter",urlPatterns = "/H2/**")
public class RequestParamFilter implements Filter {
    /**
     * @Description String.replace
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //请求转化
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        //request数据包装
        HttpServletRequestWrapper wrapper=new HttpServletRequestWrapper(request){
            //重写request.getParameter("")方法
            @Override
            public String getParameter(String name) {
                //数据获取
                String value= request.getParameter(name);
                //数据替换
                if(value.length()>0)
                    return value.replace("fuck","****");
                return super.getParameter(name);
            }
            //重写@RequestParm获取方法
            @Override
            public String[] getParameterValues(String name) {
                //同上
                String[] value= request.getParameterValues(name);
                for(int i=0;i<value.length;i++){
                    value[i]=value[i].replace("fuck","****");
                }
                return (value.length>0)?value:super.getParameterValues(name);
            }
        };
        filterChain.doFilter(wrapper, servletResponse);
    }
}
