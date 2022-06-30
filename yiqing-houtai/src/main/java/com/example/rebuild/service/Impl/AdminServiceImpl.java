package com.example.rebuild.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rebuild.entity.Admin;
import com.example.rebuild.mapper.AdminMapper;
import com.example.rebuild.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Override
    public Admin adminLogin(String username, String password) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        wrapper.eq("password", password);
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public Integer adminLogout(String token) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("token", token);
        Admin admin = baseMapper.selectOne(wrapper);
        int ret = -1;
        if(admin!=null) {
            admin.setToken("");
            ret = baseMapper.updateById(admin);
        }
        return ret;
    }

    @Override
    public Admin info(String token) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("token", token);
        return baseMapper.selectOne(wrapper);
    }

}
