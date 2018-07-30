package com.lixiaohao.test.springshiro.dao;

import com.lixiaohao.test.springshiro.model.User;

/**
 * Created by xiaohao.li on 2017/7/19.
 */
public interface UserDao {

    /***
     *  根据用户名查询
     *
     * @param userName
     * @return
     */
    User findByUserName(String userName);

    /**
     * 新增用户
     *
     * @param user
     */
    void insert(User user);

}
