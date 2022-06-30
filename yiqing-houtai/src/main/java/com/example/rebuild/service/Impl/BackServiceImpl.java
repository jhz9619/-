package com.example.rebuild.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rebuild.entity.Back;
import com.example.rebuild.entity.City;
import com.example.rebuild.entity.Province;
import com.example.rebuild.mapper.BackMapper;
import com.example.rebuild.service.BackService;
import com.example.rebuild.vo.BackVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BackServiceImpl extends ServiceImpl<BackMapper, Back> implements BackService {

    @Override
    public IPage<BackVo> select(Page<BackVo> page, Back back, String role, Integer userId, String major) {

        if("normal".equals(role) || "other".equals(role)) {
            return baseMapper.selectOwn(page, back, userId);
        }else if("admin".equals(role)){
            return baseMapper.selectMajor(page, back, major);
        }else{
            return baseMapper.select(page, back);
        }
    }

    @Override
    public List<Double> listChartData(List<String> days) {

        List<Double> values = new ArrayList<>();
        for(String _day : days){
            Double in = baseMapper.sumAmount(_day);
            values.add(in==null ? 0 : in);
        }
        return values;
    }

    @Override
    public boolean isSafe(Back back, Province province, City city) {
        return false;
    }
}
