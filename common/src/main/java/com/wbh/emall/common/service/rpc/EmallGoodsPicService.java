package com.wbh.emall.common.service.rpc;

import com.wbh.emall.common.entity.rpc.GoodsUpdateDto;

/**
 * @author WBH
 * @since 2022/3/25
 */
public interface EmallGoodsPicService {
    
    void updateGoodsPic(GoodsUpdateDto goodsUpdateDto);
    
}
