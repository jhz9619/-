package com.example.rebuild.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rebuild.entity.City;
import com.example.rebuild.mapper.CityMapper;
import com.example.rebuild.service.CityService;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CityService {

    @Override
    public void deleteCity() {
        baseMapper.deleteCity();
    }

    @Override
    public void setKey() {
        baseMapper.setKey();
    }

    @Override
    public void cancelKey() {
        baseMapper.cancelKey();
    }

}