package com.lemon.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2023-02-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("T_TRAVEL_ADMIN")
@ApiModel(value="TTravelAdmin对象", description="")
public class TTravelAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "adm_aid", type = IdType.AUTO)
    private Long admAid;

    @ApiModelProperty(value = "管理员账号")
    private String admUsername;

    @ApiModelProperty(value = "管理员密码")
    private String admPassword;

    @ApiModelProperty(value = "管理员身份证")
    @TableField("adm_ID")
    private String admId;

    @ApiModelProperty(value = "管理员性别")
    private String admSex;

    @ApiModelProperty(value = "管理员头像")
    private String admImg;

    @ApiModelProperty(value = "状态 0:禁用 1:正常")
    private String admStatus;

    @ApiModelProperty(value = "管理员手机号")
    private String admPhone;

    @ApiModelProperty(value = "管理员名称")
    private String admName;


}
