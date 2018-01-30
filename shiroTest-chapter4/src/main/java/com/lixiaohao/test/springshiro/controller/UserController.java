package com.lixiaohao.test.springshiro.controller;

import com.lixiaohao.test.springshiro.service.UserService;
import com.lixiaohao.test.springshiro.util.MD5HashUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xiaohao.li on 2017/7/19.
 */
@Controller
@RequestMapping("/shiro")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/login",method = RequestMethod.GET)
    @ResponseBody
    public String login(String userName,String passWord) {



        if ( null == userName || "".equals(userName) ) {
            return "用户名不能为空!";
        }

        if ( null == passWord || "".equals(passWord) ) {
            return "密码不能为空!";
        }

        String ciphertext = MD5HashUtil.encrypt( passWord );

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName,ciphertext);

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

        if ( subject.isAuthenticated() ) {
            return "登录成功！";
        }else {
            return "登录失败！！";
        }

    }


    @RequestMapping("/user/delete")
    @ResponseBody
    public String delete () {
        Subject subject = SecurityUtils.getSubject();

        if (  subject.isPermitted("user:delete") ) {
            return  "删除成功!";
        }else {
            return "没有删除权限啊";
        }
    }


    @RequestMapping("/user/hasRole")
    @ResponseBody
    public String hasRole () {
        Subject subject = SecurityUtils.getSubject();

        if (  subject.hasRole("administrator") ) {
            return  "好屌!";
        }else {
            return "你不是管理员，没有权限！";
        }
    }





    @RequestMapping("/user/update")
    @ResponseBody
    public String update () {

        return "更新成功";

    }

    @RequestMapping("/user/find")
    @ResponseBody
    public String find2 () {
        System.out.println("11111111111111");
        return  "success";
    }
    @RequestMapping("/anon/find")
    @ResponseBody
    public String find () {
        System.out.println("11111111111111");
        return  "success";
    }
}
