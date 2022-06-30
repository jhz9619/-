package com.example.rebuild.controller;

import com.example.rebuild.common.ServerResponse;
import com.example.rebuild.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/chart")
public class ChartController {

    @Autowired
    private UserService userService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private LogService logService;

    @Autowired
    private HealthService healthService;

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private GroupService groupService;


    @GetMapping("/panel")
    public ServerResponse panelData() {

        Map map = new HashMap();
        map.put("users",userService.count());
        map.put("logs",logService.count());
        map.put("three",noticeService.count());
        map.put("four", groupService.count());
        return ServerResponse.ofSuccess(map);

    }

    @GetMapping("/loginData")
    public ServerResponse loginData() throws ParseException {

        Map map = new HashMap();
        map.put("xData",logService.chartDay());
        map.put("expectedData",logService.chartCount());
        return ServerResponse.ofSuccess(map);

    }

    @GetMapping("/userGroupData")
    public ServerResponse userGroupData() throws ParseException {

        Map map = new HashMap();
        map.put("userGroupData",groupService.groupUsers());
        return ServerResponse.ofSuccess(map);

    }

    @GetMapping("/lineChart")
    public ServerResponse lineChart() throws ParseException {

        Map map = new HashMap();
        map.put("income", healthService.listChartData());
        map.put("outcome", leaveService.listChartData(healthService.listDay()));
        map.put("x", healthService.listDay());

        return ServerResponse.ofSuccess(map);

    }

}