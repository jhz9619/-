package com.example.rebuild.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.rebuild.entity.City;
import org.apache.ibatis.annotations.Select;

public interface CityMapper extends BaseMapper<City> {

    @Select("truncate table tb_city")
    void deleteCity();

    @Select("SET FOREIGN_KEY_CHECKS=0")
    void cancelKey();

    @Select("SET FOREIGN_KEY_CHECKS=1")
    void setKey();

}