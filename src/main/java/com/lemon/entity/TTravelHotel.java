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
@TableName("T_TRAVEL_HOTEL")
@ApiModel(value="TTravelHotel对象", description="")
public class TTravelHotel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "酒店Id")
    @TableId(value = "htl_id", type = IdType.AUTO)
    private Integer htlId;

    @ApiModelProperty(value = "酒店名称")
    private String htlName;

    @ApiModelProperty(value = "酒店图片")
    private String htlImg;

    @ApiModelProperty(value = "酒店地址")
    private String htlAddress;

    @ApiModelProperty(value = "附近景点")
    private String snicName;

    @ApiModelProperty(value = "酒店星级")
    private Integer htlStar;

    @ApiModelProperty(value = "酒店联系方式")
    private String htlPhone;

    @TableField(fill = FieldFill.INSERT)//插入时自动填充字段
    @ApiModelProperty(value = "发布时间")
    private LocalDateTime rutTime;

    @ApiModelProperty(value = "酒店简介")
    private String htlRemark;


}
