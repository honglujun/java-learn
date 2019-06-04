package com.learn.pattern.factory.abs.operation.impl;

import com.learn.pattern.factory.abs.entity.User;
import com.learn.pattern.factory.abs.operation.UserService;

/**
 * @author win10
 */
public class MySQLUserServiceImpl implements UserService {
    @Override
    public void insert(User user) {
        System.out.println("向mysql数据库User表中添加数据！");
    }

    @Override
    public User selectById(int id) {
        System.out.println("在mysql数据库User表中查询数据！");
        return null;
    }
}
