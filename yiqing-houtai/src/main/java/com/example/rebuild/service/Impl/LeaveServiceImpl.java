package com.example.rebuild.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rebuild.entity.Leave;
import com.example.rebuild.mapper.LeaveMapper;
import com.example.rebuild.service.LeaveService;
import com.example.rebuild.vo.LeaveVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaveServiceImpl extends ServiceImpl<LeaveMapper, Leave> implements LeaveService {

    @Override
    public IPage<LeaveVo> select(Page<LeaveVo> page, Leave leave, String role, Integer userId, String major) {

        if("normal".equals(role) || "other".equals(role)) {
            return baseMapper.selectOwn(page, leave, userId);
        }else if("admin".equals(role)){
            return baseMapper.selectMajor(page, leave, major);
        }else{
            return baseMapper.select(page, leave);
        }
    }

    @Override
    public List<Double> listChartData(List<String> days) {

        List<Double> values = new ArrayList<>();
        for(String _day : days){
            Double in = baseMapper.sumAmount(_day);
            values.add(in==null?0:in);
        }
        return values;
    }

}