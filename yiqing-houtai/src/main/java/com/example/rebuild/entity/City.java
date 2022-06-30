package com.example.rebuild.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@TableName("tb_city")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City extends Model<City> {

    @TableId(value = "cid",type = IdType.AUTO)
    private Integer cid;

    private String cname;
    private Integer crznum;
    private Integer cpanum;
    private Integer proid;
    private Integer citydead;
    private LocalDateTime cityupdate_time;

}