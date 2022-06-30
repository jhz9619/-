package com.example.rebuild.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rebuild.entity.Log;
import com.example.rebuild.mapper.LogMapper;
import com.example.rebuild.service.LogService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

    @Override
    public List<Integer> chartCount() throws ParseException {
        List<Integer> values = new ArrayList<>();
        List<String> days = baseMapper.chart();
        for(String _day : days){
            QueryWrapper<Log> query = new QueryWrapper<>();
            query.like("create_time",_day);
            values.add(Math.toIntExact(baseMapper.selectCount(query)));
        }
        return values;
    }

    @Override
    public List<String> chartDay() throws ParseException {
        return baseMapper.chart();
    }

}
