package com.example.rebuild.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rebuild.entity.Role;
import com.example.rebuild.mapper.RoleMapper;
import com.example.rebuild.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}

