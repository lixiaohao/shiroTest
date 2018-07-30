package com.lixiaohao.test.springshiro.controller;

import com.lixiaohao.test.springshiro.model.User;
import com.lixiaohao.test.springshiro.service.UserService;
import com.lixiaohao.test.springshiro.util.MD5HashUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiaohao.li on 2017/7/19.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    public String login(String userName,String passWord) {



        if ( null == userName || "".equals(userName) ) {
            return "用户名不能为空!";
        }

        if ( null == passWord || "".equals(passWord) ) {
            return "密码不能为空!";
        }

        String ciphertext = MD5HashUtil.encrypt( passWord );
        //用户名/密码 lixiaohao/123456

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName,ciphertext);
        usernamePasswordToken.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login( usernamePasswordToken );
        }catch (UnknownAccountException e) {
            e.printStackTrace();
            return "用户名不存在！！";
        } catch (IncorrectCredentialsException ex) {//用户名密码不匹配
            ex.printStackTrace();
            return "用户名密码不匹配！！";
        }catch (LockedAccountException lae) {// 用户被锁定
            lae.printStackTrace();
            return "用户被锁定!！";

        }catch (AuthenticationException e) {//其他的登录错误
            e.printStackTrace();
            return "登录出错！";
        }

        Session session = subject.getSession(false);
        Collection<Object> attributeKeys = session.getAttributeKeys();
        Map<Object,Object> sessions = new HashMap<Object, Object>();

        for (Object k:attributeKeys ){
            sessions.put(k,session.getAttribute(k));
        }



        if ( subject.isAuthenticated() ) {
            return "登录成功！";
        }else {
            return "登录失败！！";
        }

    }


    @RequestMapping("/delete")
    @ResponseBody
    public String delete (HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        Cookie[] cookies = request.getCookies();

        Serializable id = subject.getSession(false).getId();

        System.out.println("sessiond ---》id:"+id);


        if ( subject.isPermitted("shiro:user:delete") ) {
            return  "删除成功!";
        }else {
            return "没有删除权限啊";
        }
    }


    @RequestMapping("/hasRole")
    @ResponseBody
    public String hasRole () {
        Subject subject = SecurityUtils.getSubject();

        if (  subject.hasRole("administrator") ) {
            return  "好屌!";
        }else {
            return "你不是管理员，没有权限！";
        }
    }





    @RequestMapping("/update")
    @ResponseBody
    public String update () {

        return "更新成功";

    }

    @RequestMapping("/find")
    @ResponseBody
    public String find2 () {
        System.out.println("11111111111111");
        return  "success";
    }


    @RequestMapping(value = "/anon/find")
    @ResponseBody
    public User find (String name) {
        System.out.println("11111111111111");
        User user = new User();
        user.setId(System.currentTimeMillis());
        user.setPassWord("12345");
        user.setUserName(name);
        return  user;
    }
}
