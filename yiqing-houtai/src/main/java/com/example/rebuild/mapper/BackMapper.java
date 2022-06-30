package com.example.rebuild.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rebuild.entity.Back;
import com.example.rebuild.vo.BackVo;
import org.apache.ibatis.annotations.Select;

public interface BackMapper extends BaseMapper<Back> {

    @Select("select b.*, u.realname as username, u.major, g.remark as dept  " +
            "from tb_back b, tb_user u, tb_group g where b.user_id = u.id and u.group_id = g.id" +
            " and b.reason like concat('%',#{back.reason},'%') " +
            " and b.remark like concat('%',#{back.remark},'%') " +
            " and b.deleted = false " +
            " order by b.create_time desc")
    IPage<BackVo> select(Page<BackVo> page, Back back);

    @Select("select b.*, u.realname as username, u.major, g.remark as dept  " +
            "from tb_back b, tb_user u, tb_group g where b.user_id = u.id and u.group_id = g.id" +
            " and b.reason like concat('%',#{back.reason},'%') " +
            " and b.remark like concat('%',#{back.remark},'%') " +
            " and b.user_id = #{userId} " +
            " and b.deleted = false " +
            " order by b.create_time desc")
    IPage<BackVo> selectOwn(Page<BackVo> page, Back back, Integer userId);

    @Select("select b.*, u.realname as username, u.major, g.remark as dept  " +
            "from tb_back b, tb_user u, tb_group g where b.user_id = u.id and u.group_id = g.id" +
            " and b.reason like concat('%',#{back.reason},'%') " +
            " and b.remark like concat('%',#{back.remark},'%') " +
            " and u.major = #{major} " +
            " and b.deleted = false " +
            " order by b.create_time desc")
    IPage<BackVo> selectMajor(Page<BackVo> page, Back back, String major);

    @Select("select count(1) from tb_back where create_time like concat('%',#{createTime},'%') ")
    Double sumAmount(String createTime);

}
