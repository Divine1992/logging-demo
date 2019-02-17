package com.divine.logging.service;

import com.divine.logging.dao.dto.Success;
import com.divine.logging.dao.dto.UserModel;

import java.util.List;

public interface UserService {
    Success create(UserModel userModel);

    UserModel update(UserModel userModel);

    UserModel get(String username);

    Success delete(Long userId);

    List<UserModel> getAll();
}
