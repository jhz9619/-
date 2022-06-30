package com.example.rebuild.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//MybatisPlus配置类
@Configuration
@MapperScan("com.example.rebuild.mapper")
public class MybatisPlusConfig {

    // 分页插件
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        return new MybatisPlusInterceptor();
    }

}
