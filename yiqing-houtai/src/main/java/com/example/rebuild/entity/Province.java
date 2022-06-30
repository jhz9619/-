package com.example.rebuild.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@TableName("tb_province")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Province extends Model<Province> {

    @TableId(value = "proid",type = IdType.AUTO)
    private Integer proid;

    private String proname;
    private Integer rznum;
    private Integer panum;
    private Integer prodead;
    private LocalDateTime update_time;

}