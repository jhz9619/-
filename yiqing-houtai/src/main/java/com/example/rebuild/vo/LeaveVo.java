package com.example.rebuild.vo;

import com.example.rebuild.entity.Leave;
import lombok.Data;

@Data
public class LeaveVo extends Leave {

    private String username;
    private String major;
    private String dept;

}
