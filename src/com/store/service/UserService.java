package com.store.service;

import com.store.domain.User;

/**
 * Created by Taeyeon-Serenity on 2017/7/24.
 */
public interface UserService {
    void regist(User user) throws Exception;

    User active(String code) throws Exception;

    User login(String username, String password) throws Exception;
}
