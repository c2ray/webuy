package com.wbh.emall.user.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wbh.emall.user.common.entity.EmallGoodsCover;
import com.wbh.emall.user.common.entity.vo.EmallGoodsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author WBH
 * @since 2022/2/23
 */
public interface EmallGoodsCoverMapper extends BaseMapper<EmallGoodsCover> {
    List<EmallGoodsVo> selectRandomGoodsCover(@Param("num") Integer num);
}