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
 * @since 2023-03-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("T_TRAVEL_ROUTE")
@ApiModel(value="TTravelRoute对象", description="")
public class TTravelRoute implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键Id")
    @TableId(value = "rut_id", type = IdType.AUTO)
    private Long rutId;

    @ApiModelProperty(value = "旅游线路出发地")
    private String rutStart;

    @ApiModelProperty(value = "旅游线路目的地")
    private String rutEnd;

    @ApiModelProperty(value = "旅游线路图片")
    private String rutImg;

    @ApiModelProperty(value = "旅游线路途径地")
    private String rutMid;

    @ApiModelProperty(value = "旅游线路价格")
    private String rutPrice;

    @ApiModelProperty(value = "旅游线路类型1：跟团游 2：自由行")
    private String rutType;

    @TableField(fill = FieldFill.INSERT)//插入时自动填充字段
    @ApiModelProperty(value = "发布时间")
    private LocalDateTime rutTime;

    @ApiModelProperty(value = "旅游线路名称")
    private String rutName;


}
