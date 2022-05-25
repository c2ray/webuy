package com.wbh.emall.user.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wbh.emall.user.common.entity.EmallGoodsPic;
import com.wbh.emall.user.common.entity.vo.EmallGoodsPicVo;

import java.util.List;

/**
 * @author WBH
 * @since 2022/2/20
 */
public interface EmallGoodsPicMapper extends BaseMapper<EmallGoodsPic> {
    List<EmallGoodsPicVo> selectPicListByGid(Integer gid);
}