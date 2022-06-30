package com.example.rebuild.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.rebuild.entity.Log;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LogMapper extends BaseMapper<Log> {

    @Select("select d from (select distinct DATE_FORMAT(create_time,'%Y-%m-%d') as d   " +
            "from tb_log order by create_time desc limit 15) as tt order by d")
    List<String> chart();

}