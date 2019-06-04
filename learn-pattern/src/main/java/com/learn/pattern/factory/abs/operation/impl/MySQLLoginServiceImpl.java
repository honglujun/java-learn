package com.learn.pattern.factory.abs.operation.impl;

import com.learn.pattern.factory.abs.entity.Login;
import com.learn.pattern.factory.abs.operation.LoginService;

public class MySQLLoginServiceImpl implements LoginService {

    @Override
    public void insert(Login login) {
        System.out.println("向mysql数据库Login表中添加数据！");
    }

    @Override
    public Login selectById(int id) {
        System.out.println("在mysql数据库Login表中查询数据！");
        return null;
    }
}
