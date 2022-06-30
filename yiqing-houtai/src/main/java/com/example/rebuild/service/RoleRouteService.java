package com.example.rebuild.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rebuild.entity.RoleRoute;
import com.example.rebuild.vo.RouteVo;

import java.util.List;

public interface RoleRouteService extends IService<RoleRoute> {

    boolean updateRouteTable(List<RouteVo> routeVos, String key);

}