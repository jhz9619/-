package com.example.rebuild.controller;

import com.example.rebuild.common.ServerResponse;
import com.example.rebuild.service.RouteService;
import com.example.rebuild.vo.RouteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//Route控制器
@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping("/getRoutes/{role}")
    public ServerResponse getRoutesByRole(@PathVariable("role") String roleName) {

        List<RouteVo> routes = routeService.getRoutes(roleName);
        return ServerResponse.ofSuccess(routes);

    }

    @GetMapping("/getRoutes")
    public ServerResponse getRoutes() {

        List<RouteVo> routes = routeService.getRoutes();
        return ServerResponse.ofSuccess(routes);

    }

}