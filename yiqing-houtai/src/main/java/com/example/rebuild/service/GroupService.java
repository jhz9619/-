package com.example.rebuild.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rebuild.entity.Group;
import com.example.rebuild.vo.PieData;

import java.util.List;

public interface GroupService extends IService<Group> {

    int updateAmount(Integer groupId, Double amount);

    List<PieData> groupUsers();

}
