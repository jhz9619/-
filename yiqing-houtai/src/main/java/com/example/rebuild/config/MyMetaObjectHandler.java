package com.example.rebuild.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class MyMetaObjectHandler implements MetaObjectHandler {

    // 自动插入字段
    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasSetter("createTime")&&getFieldValByName("createTime",metaObject)==null) {
            this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
            //setInsertFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }

    // 自动更新字段
    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasSetter("updateTime")&&getFieldValByName("updateTime",metaObject)==null) {
            this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }
}
