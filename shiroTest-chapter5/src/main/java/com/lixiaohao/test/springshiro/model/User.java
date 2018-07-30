package com.lixiaohao.test.springshiro.model;

import java.io.Serializable;

/**
 * Created by xiaohao.li on 2017/7/19.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String realName;
    private String userName;
    private String passWord;
    private long id;
    private String rabbit;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRabbit() {
        return rabbit;
    }

    public void setRabbit(String rabbit) {
        this.rabbit = rabbit;
    }
}
