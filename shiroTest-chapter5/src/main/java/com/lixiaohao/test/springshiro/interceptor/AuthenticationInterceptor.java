package com.lixiaohao.test.springshiro.interceptor;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 访问控制
 *
 * Created by xiaohao.li on 2017/7/26.
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    private static Map<String,String> permissions = new HashMap<String, String>(){{
        put("/shiro/user/update","shiro:user:update");
        put("/shiro/user/find","shiro:user:find");
        put("/shiro/user/hasRole","shiro:user:hasRole");
        put("/shiro/user/delete","shiro:user:delete");
    }
    };


    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String url = getRequestUrl(httpServletRequest);

        if ( url.endsWith( "/login" ) ) {
            return true;
        }

        String permission = permissions.get(url);


        if ( !StringUtils.hasText( permission )) {
            return false;
        }

        Subject subject = SecurityUtils.getSubject();


        if ( subject.isPermitted( permission ) ) {
            return true;
        }

        return false;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    private String getRequestUrl (HttpServletRequest request) {
        String url = request.getRequestURI().toString();

        return url;
    }

}
