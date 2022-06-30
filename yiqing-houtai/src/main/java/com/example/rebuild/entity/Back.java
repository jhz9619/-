package com.example.rebuild.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("tb_back")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Back extends Model<Back> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private String remark; /*行程轨迹*/

    private String reason; /*返校原因*/

    private String applyId; /* 审批人 */

    private String applyContent;

    /* 审核状态 0-未审核 1-通过 2-拒绝 */
    private Integer status;

    @TableLogic
    private Integer deleted;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime backTime;

    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
