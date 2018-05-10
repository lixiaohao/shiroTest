package com.lixiaohao.test.springshiro.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xiaohao.li@chinaredstar.com
 * @Description:
 * @Date: Created in 16:29 2018/5/9
 */
@Controller
@RequestMapping("session")
public class SessionController {


    @RequestMapping("addSession")
    @ResponseBody
    public ResponseEntity<Map<String,String>> addSession(HttpServletRequest request, HttpServletResponse response){

        Map<String,String> result = new HashMap<String, String>();
        result.put("success","true");

        HttpSession session = request.getSession();

        session.setAttribute("employeeId","3d7b6fd7-1c1b-4c8b-aeb0-d0b473206ca93da");

        session.setAttribute("employeeId2","3d7b6fd7-1c1b-4c8b-aeb0-d0b473206ca93da2");

        return new ResponseEntity<Map<String, String>>(result,HttpStatus.OK);
    }



    @RequestMapping("checkSession")
    @ResponseBody
    public ResponseEntity<Map<String,String>> checkSession(HttpServletRequest request, HttpServletResponse response){


        Map<String,String> result = new HashMap<String, String>();
        result.put("success","true");
        HttpSession session = request.getSession(false);
        String id = session.getId();

        Object employeeId = session.getAttribute("employeeId");

        result.put("sessionId",id);
        result.put("employeeId",employeeId.toString());
        result.put("success","true");
        return new ResponseEntity<Map<String,String>>(result, HttpStatus.OK);
    }





    @RequestMapping("addCookies")
    @ResponseBody
    public ResponseEntity<Map<String,String>> addCookies(HttpServletRequest request, HttpServletResponse response){

        Map<String,String> result = new HashMap<String, String>();
        result.put("success","true");

        Cookie cookie = new Cookie("testcookie1", "41424235524");
//        cookie.setDomain(".rs.com");

        response.addCookie(cookie);
        response.addCookie(new Cookie("testcookie","124324dfasfczxvzv"));

        return new ResponseEntity<Map<String, String>>(result,HttpStatus.OK);
    }

    @RequestMapping("checkCookies")
    @ResponseBody
    public ResponseEntity<Map<String,String>> checkCookies(HttpServletRequest request, HttpServletResponse response){

        Map<String,String> result = new HashMap<String, String>();
        result.put("success","true");
        Cookie[] cookies = request.getCookies();

        for ( Cookie cookie:cookies ) {
            result.put(cookie.getName(),cookie.getValue());
        }

        result.put("success","true");
        return new ResponseEntity<Map<String,String>>(result, HttpStatus.OK);
    }


}
