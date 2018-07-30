package com.lixiaohao.test.springshiro.model;

import org.apache.shiro.session.mgt.SimpleSession;

import java.io.Serializable;

/**
 * @program: shiroTest
 * @description:
 * @author: xiaohao.li
 * @create: 2018-07-27 17:08
 **/

public class SessionModel implements Serializable{
    private String id;
    private String sessionId;

    private String value;

    private int status;

    private String sessionStr;

    private SimpleSession session;

    public SessionModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSessionStr() {
        return sessionStr;
    }

    public void setSessionStr(String sessionStr) {
        this.sessionStr = sessionStr;
    }

    public SimpleSession getSession() {
        return session;
    }

    public void setSession(SimpleSession session) {
        this.session = session;
    }
}
