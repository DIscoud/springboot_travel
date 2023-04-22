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
 * @since 2023-03-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("T_TRAVEL_SCENIC")
@ApiModel(value="TTravelScenic对象", description="")
public class TTravelScenic implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "旅游景点Id")
    @TableId(value = "snic_id", type = IdType.AUTO)
    private Long snicId;

    @ApiModelProperty(value = "旅游景点名称")
    private String snicName;

    @ApiModelProperty(value = "旅游景点图片")
    private String snicImg;

    @ApiModelProperty(value = "旅游景点等级")
    private String snicRank;

    @ApiModelProperty(value = "门票")
    private String snicPrice;

    @ApiModelProperty(value = "旅游景点地址")
    private String snicAddress;

    @ApiModelProperty(value = "旅游景点所属省份")
    private String snicProvince;

    @ApiModelProperty(value = "旅游景点简介")
    private String snicRemark;

    @ApiModelProperty(value = "开放时间")
    private String snicTime;

    @ApiModelProperty(value = "是否热门 1热门 2 不热门")
    private Integer snicHot;

    @TableField(fill = FieldFill.INSERT)//插入时自动填充字段
    @ApiModelProperty(value = "发布时间")
    private LocalDateTime rutTime;


}
