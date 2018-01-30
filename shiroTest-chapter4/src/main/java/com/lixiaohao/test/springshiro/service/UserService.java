package com.lixiaohao.test.springshiro.service;

import com.lixiaohao.test.springshiro.dao.UserDao;
import com.lixiaohao.test.springshiro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiaohao.li on 2017/7/19.
 */
@Service("userService")
public class UserService {

    @Autowired
    private UserDao userDao;

    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    public void insert(User user) {
         userDao.insert(user);
    }

}
