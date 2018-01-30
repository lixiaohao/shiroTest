package com.lixiaohao.test.springshiro.filter;

import org.apache.log4j.Logger;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by xiaohao.li on 2017/7/24.
 */
@Order(1)
@WebFilter(filterName = "userFilter",urlPatterns = "/shiro/*")
public class UserFilter implements Filter{

    Logger logger = Logger.getLogger(UserFilter.class.getName());

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        logger.info("开始进入filler中：info {} " + request.getRequestURI());
        try {
            filterChain.doFilter(servletRequest,servletResponse);
        }catch (Exception e){

            logger.error("错误日志 {} " + e.getMessage() );

            e.printStackTrace();
        }
    }

    public void destroy() {

    }
}
