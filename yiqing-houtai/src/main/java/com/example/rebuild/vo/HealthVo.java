package com.example.rebuild.vo;

import com.example.rebuild.entity.Health;
import lombok.Data;

@Data
public class HealthVo extends Health {

    private String username;
    private String major;
    private String dept;

}