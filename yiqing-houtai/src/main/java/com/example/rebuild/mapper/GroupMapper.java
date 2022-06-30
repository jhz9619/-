package com.example.rebuild.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.rebuild.entity.Group;
import com.example.rebuild.vo.PieData;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GroupMapper extends BaseMapper<Group> {

    @Select("select  a.remark as name,count(b.id) as value from  tb_group a, tb_user b where b.group_id = a.id group by a.remark")
    List<PieData> groupUsers();
}
