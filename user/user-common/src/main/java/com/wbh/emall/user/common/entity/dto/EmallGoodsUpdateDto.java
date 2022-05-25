package com.wbh.emall.user.common.entity.dto;

import com.wbh.emall.user.common.entity.EmallGoods;
import com.wbh.emall.user.common.entity.vo.EmallGoodsCoverVo;
import com.wbh.emall.user.common.entity.vo.EmallGoodsPicVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author WBH
 * @since 2022/3/15
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Data
public class EmallGoodsUpdateDto extends EmallGoods {
    
    private EmallGoodsCoverVo coverImg;
    
    private List<EmallGoodsPicVo> imgs;
    
    private List<EmallGoodsPicVo> deleteImgs;
}
