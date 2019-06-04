package com.learn.pattern.factory.abs.factory.impl;

import com.learn.pattern.factory.abs.factory.FactoryAbsService;
import com.learn.pattern.factory.abs.operation.LoginService;
import com.learn.pattern.factory.abs.operation.UserService;
import com.learn.pattern.factory.abs.operation.impl.OracleLoginServiceImpl;
import com.learn.pattern.factory.abs.operation.impl.OracleUserServiceImpl;

/**
 * @author win10
 */
public class FactoryAbsOracleServiceImpl implements FactoryAbsService {
    @Override
    public UserService createUser() {
        return new OracleUserServiceImpl();
    }

    @Override
    public LoginService createLogin() {
        return new OracleLoginServiceImpl();
    }
}
