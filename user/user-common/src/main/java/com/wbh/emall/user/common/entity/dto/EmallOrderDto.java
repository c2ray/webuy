package com.wbh.emall.user.common.entity.dto;

import com.wbh.emall.user.common.entity.EmallGoodsOrderRelation;
import com.wbh.emall.user.common.entity.EmallOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import java.util.List;

/**
 * @author WBH
 * @since 2022/3/20
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Data
@FieldNameConstants
public class EmallOrderDto extends EmallOrder {
    
    private List<EmallGoodsOrderRelation> orderRelations;
    
}
