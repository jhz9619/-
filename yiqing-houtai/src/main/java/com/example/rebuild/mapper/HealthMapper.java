package com.example.rebuild.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rebuild.entity.Health;
import com.example.rebuild.vo.HealthVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HealthMapper extends BaseMapper<Health> {

    @Select("<script>" +
            "select h.*, u.realname as username, u.major, g.remark as dept  " +
            "from  tb_health h, tb_user u, tb_group g where h.user_id = u.id and u.group_id = g.id" +
            " <if test=\"health.cough != null \" >" +
            " and h.cough = #{health.cough} " +
            " </if>" +
            " <if test=\"health.fever != null \" >" +
            " and h.fever = #{health.fever} " +
            " </if>" +
            " <if test=\"health.temperature != null \" >" +
            " and h.temperature > #{health.temperature} " +
            " </if>" +
            " and h.deleted = false " +
            " order by h.create_time desc" +
            " </script>")
    IPage<HealthVo> select(Page<HealthVo> page, Health health);

    @Select("<script>" +
            "select h.*, u.realname as username, u.major, g.remark as dept  " +
            "from  tb_health h, tb_user u, tb_group g where h.user_id = u.id and u.group_id = g.id" +
            " <if test=\"health.cough != null \" >" +
            " and h.cough = #{health.cough} " +
            " </if>" +
            " <if test=\"health.fever != null \" >" +
            " and h.fever = #{health.fever} " +
            " </if>" +
            " <if test=\"health.temperature != null \" >" +
            " and h.temperature > #{health.temperature} " +
            " </if>" +
            " and h.user_id = #{userId} " +
            " and h.deleted = false " +
            " order by h.create_time desc" +
            " </script>")
    IPage<HealthVo> selectOwn(Page<HealthVo> page, Health health, Integer userId);

    @Select("<script>" +
            "select h.*, u.realname as username, u.major, g.remark as dept  " +
            "from  tb_health h, tb_user u, tb_group g where h.user_id = u.id and u.group_id = g.id" +
            " <if test=\"health.cough != null \" >" +
            " and h.cough = #{health.cough} " +
            " </if>" +
            " <if test=\"health.fever != null \" >" +
            " and h.fever = #{health.fever} " +
            " </if>" +
            " <if test=\"health.temperature != null \" >" +
            " and h.temperature > #{health.temperature} " +
            " </if>" +
            " and u.major = #{major} " +
            " and h.deleted = false " +
            " order by h.create_time desc" +
            " </script>")
    IPage<HealthVo> selectMajor(Page<HealthVo> page, Health health, String major);

    @Select("select  distinct DATE_FORMAT(create_time,'%Y-%m-%d') from  tb_health order by create_time asc limit 7")
    List<String> dayData();

    @Select("select count(1)  from  tb_health where create_time like concat('%',#{createTime},'%') ")
    Double sumAmount(String createTime);
}

