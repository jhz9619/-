package com.example.rebuild.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rebuild.entity.Admin;

public interface AdminService extends IService<Admin> {

    Admin adminLogin(String username, String password);

    Integer adminLogout(String token);

    /* 根据token 获取Admin */
    Admin info(String token);

}