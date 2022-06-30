package com.example.rebuild.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rebuild.entity.Health;
import com.example.rebuild.vo.HealthVo;

import java.util.List;

public interface HealthService extends IService<Health> {

    IPage<HealthVo> select(Page<HealthVo> page, Health health, String role, Integer userId, String major);

    List<String> listDay();

    List<Double> listChartData();

}