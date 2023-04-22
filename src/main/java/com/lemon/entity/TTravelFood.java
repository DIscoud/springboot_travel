package com.lemon.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("T_TRAVEL_FOOD")
@ApiModel(value="TTravelFood对象", description="")
public class TTravelFood implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "美食Id")
    @TableId(value = "food_id", type = IdType.AUTO)
    private Long foodId;

    @ApiModelProperty(value = "美食名称")
    private String foodName;

    @ApiModelProperty(value = "美食图片")
    private String foodImg;

    @ApiModelProperty(value = "美食分类")
    private String foodCategory;

    @ApiModelProperty(value = "附近景点")
    private String snicName;

    @ApiModelProperty(value = "人均价格")
    private String foodPrice;

    @ApiModelProperty(value = "美食简介")
    private String foodRemark;

    @ApiModelProperty(value = "是否热门 1：热门 2：正常")
    private String foodHot;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime rutTime;


}
