package com.example.rebuild.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rebuild.entity.Back;
import com.example.rebuild.entity.City;
import com.example.rebuild.entity.Province;
import com.example.rebuild.vo.BackVo;

import java.util.List;

public interface BackService extends IService<Back> {

    IPage<BackVo> select(Page<BackVo> page, Back back, String role, Integer userId, String major);

    List<Double> listChartData(List<String> days);

    boolean isSafe(Back back, Province province, City city);

}

