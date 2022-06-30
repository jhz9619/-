package com.example.rebuild.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.rebuild.entity.Province;
import org.apache.ibatis.annotations.Select;

public interface ProvinceMapper extends BaseMapper<Province> {

    @Select("truncate table tb_province")
    void deleteProvince();

}