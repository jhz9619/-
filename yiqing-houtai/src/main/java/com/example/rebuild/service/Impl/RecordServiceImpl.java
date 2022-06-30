package com.example.rebuild.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rebuild.entity.Back;
import com.example.rebuild.entity.Leave;
import com.example.rebuild.entity.Record;
import com.example.rebuild.mapper.RecordMapper;
import com.example.rebuild.service.RecordService;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {

    @Override
    public void addRecord(Leave leave, String content) {
        Record record = new Record();
        record.setApplyName(leave.getApplyId());
        record.setApplyContent(content);
        record.setApplyId(leave.getId());
        baseMapper.insert(record);
    }

    @Override
    public void applyRecord(Leave leave) {
        Record record = new Record();
        record.setApplyName(leave.getApplyId());
        record.setApplyContent(leave.getApplyContent());
        record.setApplyId(leave.getId());
        baseMapper.insert(record);
    }

    @Override
    public void addRecord2(Back back, String content) {
        Record record = new Record();
        record.setApplyName(back.getApplyId());
        record.setApplyContent(content);
        record.setApplyId(back.getId());
        baseMapper.insert(record);
    }

    @Override
    public void applyRecord2(Back back) {
        Record record = new Record();
        record.setApplyName(back.getApplyId());
        record.setApplyContent(back.getApplyContent());
        record.setApplyId(back.getId());
        baseMapper.insert(record);
    }

}
