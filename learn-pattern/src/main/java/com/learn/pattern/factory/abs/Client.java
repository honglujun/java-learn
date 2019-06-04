package com.learn.pattern.factory.abs;

import com.learn.pattern.factory.abs.entity.Login;
import com.learn.pattern.factory.abs.entity.User;
import com.learn.pattern.factory.abs.factory.FactoryAbsService;
import com.learn.pattern.factory.abs.factory.impl.FactoryAbsMySQLServiceImpl;
import com.learn.pattern.factory.abs.factory.impl.FactoryAbsOracleServiceImpl;

/**
 * @author win10
 */
public class Client {

    public static void main(String[] args) throws Exception {

        // 通过反射拿到工厂接口类
        FactoryAbsService mySQLFactory = (FactoryAbsService) Class.forName("com.learn.factory.abs.factory.impl.FactoryAbsMySQLServiceImpl").newInstance();
        FactoryAbsService oracleFactory = (FactoryAbsService) Class.forName("com.learn.factory.abs.factory.impl.FactoryAbsOracleServiceImpl").newInstance();
        // 直接new拿到工厂接口类
        FactoryAbsService mySQLFactoryNew = new FactoryAbsMySQLServiceImpl();
        FactoryAbsService oracleFactoryNew = new FactoryAbsOracleServiceImpl();

        User user = new User();
        Login login = new Login();
        mySQLFactory.createUser().insert(user);
        mySQLFactory.createLogin().insert(login);
        mySQLFactory.createUser().selectById(1);
        mySQLFactory.createLogin().selectById(1);

        oracleFactory.createUser().insert(user);
        oracleFactory.createLogin().insert(login);
        oracleFactory.createUser().selectById(2);
        oracleFactory.createLogin().selectById(2);

        mySQLFactoryNew.createUser().insert(user);
        mySQLFactoryNew.createLogin().insert(login);
        mySQLFactoryNew.createUser().selectById(3);
        mySQLFactoryNew.createLogin().selectById(3);

        oracleFactoryNew.createUser().insert(user);
        oracleFactoryNew.createLogin().insert(login);
        oracleFactoryNew.createUser().selectById(4);
        oracleFactoryNew.createLogin().selectById(4);
    }
}
