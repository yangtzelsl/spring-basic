package com.yangtzelsl.ioc.service;

import com.yangtzelsl.ioc.dao.UserDao;

import javax.annotation.Resource;

public class UserService {

    //创建UserDao类型属性，生成set方法
    @Resource
    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add() {
        System.out.println("service add...............");
        userDao.update();
    }
}
