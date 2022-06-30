package com.example.rebuild.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.rebuild.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface AdminMapper extends BaseMapper<Admin> {

    @Select("SELECT * FROM tb_admin WHERE admin_no=#{account} AND password=#{password}" +
            "        UNION" +
            "        SELECT * FROM tb_admin WHERE username=#{account} AND password=#{password}" +
            "        UNION" +
            "        SELECT * FROM tb_admin WHERE realname=#{account} AND password=#{password}")
    Admin adminLogin(@Param("account") String account, @Param("password") String password);

}

