package com.lixiaohao.test.springshiro.filter;

import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 访问控制
 *
 * Created by xiaohao.li on 2017/7/26.
// */
//@Order(2)
@WebFilter(filterName = "permisionFilter",urlPatterns = "/shiro/*")
public class AuthenticationFilter extends AuthorizationFilter  {


    private static Map<String,String> permissions = new HashMap<String, String>(){{
        put("/shiro/user/update","shiro:user:update");
        put("/shiro/user/find","shiro:user:find");
        put("/shiro/user/hasRole","shiro:user:hasRole");
        put("/shiro/user/delete","shiro:user:delete");
    }
    };


    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {

//       Subject subject = getSubject(servletRequest,servletResponse);
//
//
//        HttpServletRequest request = (HttpServletRequest)servletRequest;
//        String url = getRequestUrl(request);
//
//        if ( subject.isPermitted(permissions.get(url)) ) {
//            return true;
//        }

        return true;
    }


    private String getRequestUrl (HttpServletRequest request) {
        String url = request.getRequestURI().toString();

        return url;
    }
}
