package com.example.rebuild.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rebuild.entity.Group;
import com.example.rebuild.mapper.GroupMapper;
import com.example.rebuild.service.GroupService;
import com.example.rebuild.vo.PieData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {

    @Override
    public int updateAmount(Integer groupId, Double amount) {
        Group group = baseMapper.selectById(groupId);
        Double originalAmount = group.getAmount();
        group.setAmount(originalAmount + amount);
        return baseMapper.updateById(group);
    }

    @Override
    public List<PieData> groupUsers() {
        return baseMapper.groupUsers();
    }

}
