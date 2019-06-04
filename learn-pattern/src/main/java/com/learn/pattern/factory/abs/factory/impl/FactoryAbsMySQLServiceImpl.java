package com.learn.pattern.factory.abs.factory.impl;


import com.learn.pattern.factory.abs.factory.FactoryAbsService;
import com.learn.pattern.factory.abs.operation.LoginService;
import com.learn.pattern.factory.abs.operation.UserService;
import com.learn.pattern.factory.abs.operation.impl.MySQLLoginServiceImpl;
import com.learn.pattern.factory.abs.operation.impl.MySQLUserServiceImpl;

/**
 * @author win10
 */
public class FactoryAbsMySQLServiceImpl implements FactoryAbsService {
    @Override
    public UserService createUser() {
        return new MySQLUserServiceImpl();
    }

    @Override
    public LoginService createLogin() {
        return new MySQLLoginServiceImpl();
    }
}
