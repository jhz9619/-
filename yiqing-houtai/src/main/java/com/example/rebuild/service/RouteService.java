package com.example.rebuild.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rebuild.entity.Route;
import com.example.rebuild.vo.RouteVo;

import java.util.List;

public interface RouteService extends IService<Route> {

    List<RouteVo> getRoutes();

    List<RouteVo> getRoutes(String roleName);

}