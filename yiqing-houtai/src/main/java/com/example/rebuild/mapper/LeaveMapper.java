package com.example.rebuild.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rebuild.entity.Leave;
import com.example.rebuild.vo.LeaveVo;
import org.apache.ibatis.annotations.Select;

public interface LeaveMapper extends BaseMapper<Leave>{

    @Select("select l.*, u.realname as username, u.major, g.remark as dept  " +
            "from  tb_leave l, tb_user u, tb_group g where l.user_id = u.id and u.group_id = g.id" +
            " and l.reason like concat('%',#{leave.reason},'%') " +
            " and l.remark like concat('%',#{leave.remark},'%') " +
            " and l.deleted = false " +
            " order by l.create_time desc")
    IPage<LeaveVo> select(Page<LeaveVo> page, Leave leave);

    @Select("select l.*, u.realname as username, u.major, g.remark as dept  " +
            "from  tb_leave l, tb_user u, tb_group g where l.user_id = u.id and u.group_id = g.id" +
            " and l.reason like concat('%',#{leave.reason},'%') " +
            " and l.remark like concat('%',#{leave.remark},'%') " +
            " and l.user_id = #{userId} " +
            " and l.deleted = false " +
            " order by l.create_time desc")
    IPage<LeaveVo> selectOwn(Page<LeaveVo> page, Leave leave, Integer userId);

    @Select("select l.*, u.realname as username, u.major, g.remark as dept  " +
            "from  tb_leave l, tb_user u, tb_group g where l.user_id = u.id and u.group_id = g.id" +
            " and l.reason like concat('%',#{leave.reason},'%') " +
            " and l.remark like concat('%',#{leave.remark},'%') " +
            " and u.major = #{major} " +
            " and l.deleted = false " +
            " order by l.create_time desc")
    IPage<LeaveVo> selectMajor(Page<LeaveVo> page, Leave leave, String major);

    @Select("select count(1)  from  tb_leave where create_time like concat('%',#{createTime},'%') ")
    Double sumAmount(String createTime);

}

