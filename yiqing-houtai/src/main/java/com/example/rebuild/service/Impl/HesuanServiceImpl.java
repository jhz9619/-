package com.example.rebuild.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rebuild.entity.Hesuan;
import com.example.rebuild.mapper.HesuanMapper;
import com.example.rebuild.service.HesuanService;
import com.example.rebuild.vo.HesuanVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HesuanServiceImpl extends ServiceImpl<HesuanMapper, Hesuan> implements HesuanService {

    @Override
    public IPage<HesuanVo> select(Page<HesuanVo> page, Hesuan hesuan, String role, Integer userId, String major) {
        if("normal".equals(role) || "other".equals(role)) {
            return baseMapper.selectOwn(page, hesuan, userId);
        }else if("admin".equals(role)){
            return baseMapper.selectMajor(page, hesuan, major);
        }else{
            return baseMapper.select(page, hesuan);
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
