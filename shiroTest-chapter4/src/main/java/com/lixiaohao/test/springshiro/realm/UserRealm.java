package com.lixiaohao.test.springshiro.realm;

import com.lixiaohao.test.springshiro.model.User;
import com.lixiaohao.test.springshiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by xiaohao.li on 2017/7/19.
 */
@Component("myRealm")
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String username = (String) principalCollection.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo  =  new SimpleAuthorizationInfo();

        //添加角色
        Set<String> roles = new HashSet<String>();
        roles.add("administrator");
        roles.add("manager");

        authorizationInfo.setRoles( roles );

        //添加权限
        Set<String> permissions = new HashSet<String>();
        permissions.add("shiro:user:add");
        permissions.add("shiro:user:update");
        permissions.add("shiro:user:find");
        authorizationInfo.setStringPermissions(permissions);

        return authorizationInfo;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       //获取token
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String userName = token.getUsername();

        User   user     = userService.findByUserName(userName);

        String passWord = user.getPassWord();

        String salt = "123456789011";
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName,passWord,salt);

        return info;
    }
}
