package com.example.rebuild.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rebuild.entity.Back;
import com.example.rebuild.entity.Leave;
import com.example.rebuild.entity.Record;

public interface RecordService extends IService<Record> {

    void addRecord(Leave leave, String content);

    void applyRecord(Leave leave);

    void addRecord2(Back back, String content);

    void applyRecord2(Back back);

}