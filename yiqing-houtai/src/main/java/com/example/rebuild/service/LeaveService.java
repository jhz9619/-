package com.example.rebuild.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rebuild.entity.Leave;
import com.example.rebuild.vo.LeaveVo;

import java.util.List;

public interface LeaveService extends IService<Leave> {

    IPage<LeaveVo> select(Page<LeaveVo> page, Leave leave, String role, Integer userId, String major);

    List<Double> listChartData(List<String> days);

}
