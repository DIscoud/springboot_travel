package com.lemon.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author lemon
 * @since 2023-03-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("T_TRAVEL_USER")
@ApiModel(value="TTravelUser对象", description="")
public class TTravelUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableId(value = "user_uid", type = IdType.AUTO)
    private Long userUid;

    @ApiModelProperty(value = "用户账号")
    private String userUsername;

    @ApiModelProperty(value = "用户密码")
    private String userPassword;

    @ApiModelProperty(value = "用户身份证")
    @TableField("user_ID")
    private String userId;

    @ApiModelProperty(value = "用户性别 1：男 0 女")
    private String userSex;

    @ApiModelProperty(value = "用户头像")
    private String userImg;

    @ApiModelProperty(value = "用户状态 0禁用 1正常")
    private String userStatus;

    @ApiModelProperty(value = "用户手机号")
    private String userPhone;

    @ApiModelProperty(value = "用户昵称")
    private String userName;

    @ApiModelProperty(value = "角色")
    private int state;

    @TableField(fill = FieldFill.INSERT)//插入时自动填充字段
    @ApiModelProperty(value = "注册时间")
    private LocalDateTime rutTime;


}
