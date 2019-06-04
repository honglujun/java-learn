package com.learn.pattern.factory.abs.operation;

import com.learn.pattern.factory.abs.entity.User;

/**
 * @author win10
 */
public interface UserService {
    /**
     * 插入操作
     *
     * @param user
     */
    void insert(User user);

    /**
     * 查询操作
     *
     * @param id
     * @return
     */
    User selectById(int id);
}
