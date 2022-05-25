package com.wbh.emall.user.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wbh.emall.user.common.entity.EmallOrder;
import com.wbh.emall.user.common.entity.vo.EmalEUserOrderVo;
import com.wbh.emall.user.common.entity.vo.EmallGoodsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author WBH
 * @since 2022/2/25
 */
public interface EmallOrderMapper extends BaseMapper<EmallOrder> {
    List<EmallOrder> selectOrderByUserId(Integer uid);
    
    IPage<EmalEUserOrderVo> selectOrderByPage(IPage<?> page, @Param("uid") Integer uid);
}