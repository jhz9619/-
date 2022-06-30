package com.example.rebuild.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rebuild.entity.User;

public interface UserService extends IService<User> {

    User login(String username, String password);

    User info(Integer id);
}

