package com.example.rebuild.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rebuild.entity.Health;
import com.example.rebuild.mapper.HealthMapper;
import com.example.rebuild.service.HealthService;
import com.example.rebuild.vo.HealthVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HealthServiceImpl extends ServiceImpl<HealthMapper, Health> implements HealthService {

    @Override
    public IPage<HealthVo> select(Page<HealthVo> page, Health health, String role, Integer userId, String major) {
        if("normal".equals(role) || "other".equals(role)) {
            return baseMapper.selectOwn(page, health, userId);
        }else if("admin".equals(role)){
            return baseMapper.selectMajor(page, health, major);
        }else{
            return baseMapper.select(page, health);
        }
    }

    @Override
    public List<String> listDay() {
        return baseMapper.dayData();
    }

    @Override
    public List<Double> listChartData() {
        List<Double> values = new ArrayList<>();
        List<String> days = baseMapper.dayData();
        for(String _day : days){
            Double in = baseMapper.sumAmount(_day);
            values.add(in==null?0:in);
        }
        return values;
    }

}
