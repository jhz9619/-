package com.example.rebuild.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rebuild.entity.RoleRoute;
import com.example.rebuild.entity.Route;
import com.example.rebuild.mapper.RoleRouteMapper;
import com.example.rebuild.mapper.RouteMapper;
import com.example.rebuild.service.RoleRouteService;
import com.example.rebuild.vo.RouteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleRouteServiceImpl extends ServiceImpl<RoleRouteMapper, RoleRoute> implements RoleRouteService {

    @Autowired
    private RouteMapper routeMapper;

    @Override
    public boolean updateRouteTable(List<RouteVo> routeVos, String key) {
        QueryWrapper<RoleRoute> wrapper = new QueryWrapper<>();
        wrapper.eq("role_name", key);
        baseMapper.delete(wrapper);
        routeVos.forEach(routeVo -> {
            RoleRoute rr = new RoleRoute();
            rr.setRoleName(key);
            rr.setRouteId(routeVo.getId());
            baseMapper.insert(rr);
            //这个仅针对一级菜单的情况使用
            QueryWrapper<Route> childQuery = new QueryWrapper<>();
            childQuery.eq("pid", routeVo.getId());
            List<Route> children = routeMapper.selectList(childQuery);
            children.forEach(child->{
                RoleRoute rr2 = new RoleRoute();
                rr2.setRoleName(key);
                rr2.setRouteId(child.getId());
                baseMapper.insert(rr2);
            });
        });
        return true;
    }
}
