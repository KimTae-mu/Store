package com.store.dao;

import com.store.domain.User;

/**
 * Created by Taeyeon-Serenity on 2017/7/24.
 */
public interface UserDao {
    void add(User user) throws Exception;

    User getByCode(String code) throws Exception;

    void update(User user) throws Exception;

    User getByUserNameAndPwd(String username, String password) throws Exception;
}
