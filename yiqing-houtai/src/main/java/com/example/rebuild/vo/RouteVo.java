package com.example.rebuild.vo;

import com.example.rebuild.entity.Route;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RouteVo extends Route {

    private Meta meta;

    private List<RouteVo> children;

    public RouteVo(){
        this.children = new ArrayList<>();
    }

}