package com.learn.pattern.factory.abs.operation;


import com.learn.pattern.factory.abs.entity.Login;

public interface LoginService {
    /**
     * 插入操作
     *
     * @param login
     */
    void insert(Login login);

    /**
     * 查询操作
     *
     * @param id
     * @return
     */
    Login selectById(int id);
}
