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
@TableName("T_FOOD_CATEGORY")
@ApiModel(value="TFoodCategory对象", description="")
public class TFoodCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "美食分类Id")
    @TableId(value = "category_id", type = IdType.AUTO)
    private Integer categoryId;

    @ApiModelProperty(value = "美食分类名称")
    private String categoryName;

    @TableField(fill = FieldFill.INSERT)//插入时自动填充字段
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime rutTime;


}
