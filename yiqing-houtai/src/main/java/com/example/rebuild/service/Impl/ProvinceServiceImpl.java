package com.example.rebuild.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rebuild.entity.Province;
import com.example.rebuild.mapper.ProvinceMapper;
import com.example.rebuild.service.ProvinceService;
import org.springframework.stereotype.Service;

@Service
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, Province> implements ProvinceService {

    @Override
    public void deleteProvince() {
        baseMapper.deleteProvince();
    }

}
