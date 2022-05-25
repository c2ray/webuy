package com.wbh.emall.user.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author WBH
* @since 2022/3/11
*/
@ApiModel(value="emall_goods_unit")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "emall_goods_unit")
public class EmallGoodsUnit {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="")
    private Integer id;

    /**
     * 商品单位
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value="商品单位")
    private String name;
}