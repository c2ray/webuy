package com.wbh.emall.user.common.entity.vo;

import com.wbh.emall.user.common.entity.EmallGoods;
import com.wbh.emall.user.common.entity.EmallGoodsCover;
import com.wbh.emall.user.common.entity.EmallGoodsPic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import java.util.List;

/**
 * @author WBH
 * @since 2022/2/23
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@FieldNameConstants
public class EmallGoodsVo extends EmallGoods {
    EmallGoodsCoverVo coverImg;
    
    List<EmallGoodsPicVo> imgs;
    
    String coverUrl;
    String typeName;
    String unitName;
    /**
     * 商品购买数量
     */
    Integer num;
    
    /**
     * 商品的合法状态
     */
    Integer legal;
}
