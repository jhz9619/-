package com.example.rebuild.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rebuild.entity.Hesuan;
import com.example.rebuild.vo.HesuanVo;

import java.util.List;

public interface HesuanService extends IService<Hesuan> {

    IPage<HesuanVo> select(Page<HesuanVo> page, Hesuan hesuan, String role, Integer userId, String major);

    List<String> listDay();

    List<Double> listChartData();

}
