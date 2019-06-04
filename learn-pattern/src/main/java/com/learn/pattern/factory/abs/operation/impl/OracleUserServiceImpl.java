package com.learn.pattern.factory.abs.operation.impl;

import com.learn.pattern.factory.abs.entity.User;
import com.learn.pattern.factory.abs.operation.UserService;

/**
 * @author win10
 */
public class OracleUserServiceImpl implements UserService {
    @Override
    public void insert(User user) {
        System.out.println("向Oracle数据库User表中添加数据！");
    }

    @Override
    public User selectById(int id) {
        System.out.println("向Oracle数据库User表中查询数据！");
        return null;
    }
}
