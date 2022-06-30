package com.example.rebuild.vo;

import com.example.rebuild.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class RoleVo extends Role {

    private List<RouteVo> routes;

}