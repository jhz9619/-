package com.example.rebuild.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("tb_admin")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Admin extends Model<Admin> {

    private static final long serialVersionUID=1L;

    private Integer id;
    private String adminNo;
    private String username;
    private String password;
    private String realname;
    private Integer userType;
    private String jobtitle;
    private String teach;
    private String telephone;
    private String email;
    private String license;
    private Integer age;
    private String avatar;
    private String address;
    private String description;
    private String remark;
    private Integer piority;
    private Integer power;
    private Integer type;
    private String token;
    private Integer status;
    private String roles;

    @TableLogic
    private Integer deleted;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
