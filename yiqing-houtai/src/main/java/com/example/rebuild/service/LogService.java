package com.example.rebuild.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rebuild.entity.Log;

import java.text.ParseException;
import java.util.List;

public interface LogService extends IService<Log> {

    List<Integer> chartCount() throws ParseException;

    List<String> chartDay() throws ParseException;

}
