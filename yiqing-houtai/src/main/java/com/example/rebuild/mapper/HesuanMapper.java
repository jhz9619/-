package com.example.rebuild.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.rebuild.entity.Hesuan;
import com.example.rebuild.vo.HesuanVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HesuanMapper extends BaseMapper<Hesuan> {

    @Select("select h.*, u.realname as username, u.major, g.remark as dept  " +
            "from tb_hesuan h, tb_user u, tb_group g where h.user_id = u.id and u.group_id = g.id" +
            " and h.deleted = false " +
            " order by h.create_time desc" )
    IPage<HesuanVo> select(Page<HesuanVo> page, Hesuan hesuan);

    @Select("select h.*, u.realname as username, u.major, g.remark as dept  " +
            "from tb_hesuan h, tb_user u, tb_group g where h.user_id = u.id and u.group_id = g.id" +
            " and h.user_id = #{userId} " +
            " and h.deleted = false " +
            " order by h.create_time desc" )
    IPage<HesuanVo> selectOwn(Page<HesuanVo> page, Hesuan hesuan, Integer userId);

    @Select("select h.*, u.realname as username, u.major, g.remark as dept  " +
            "from tb_hesuan h, tb_user u, tb_group g where h.user_id = u.id and u.group_id = g.id" +
            " and u.major = #{major} " +
            " and h.deleted = false " +
            " order by h.create_time desc" )
    IPage<HesuanVo> selectMajor(Page<HesuanVo> page, Hesuan hesuan, String major);

    @Select("select distinct DATE_FORMAT(create_time,'%Y-%m-%d') from tb_hesuan order by create_time asc limit 7")
    List<String> dayData();

    @Select("select count(1) from tb_hesuan where create_time like concat('%',#{createTime},'%') ")
    Double sumAmount(String createTime);

}
