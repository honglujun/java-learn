package com.learn.pattern.factory.abs.factory;

import com.learn.pattern.factory.abs.operation.LoginService;
import com.learn.pattern.factory.abs.operation.UserService;

/**
 * 用于生产访问user表和login表的对象
 * @author win10
 */
public interface FactoryAbsService {
    /**
     * 用于访问User方法
     * @return
     */
    UserService createUser();

    /**
     * 用于访问Login方法
     * @return
     */
    LoginService createLogin();
}
