package com.example.rebuild.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("tb_user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User extends Model<User> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userId;
    private Integer groupId;
    private String username;
    private String password;
    private String realname;
    private Integer userType;
    //private String idno;
    private String telephone;
    private String email;
    private Integer age;
    private String avatar;
    private String address;
    //private String description;
    private String remark;
    //private Integer status;
    private String roles;
    private String major;

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
