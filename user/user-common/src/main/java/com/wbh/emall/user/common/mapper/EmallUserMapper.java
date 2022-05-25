package com.wbh.emall.user.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wbh.emall.user.common.entity.EmallUser;

/**
 * @author WBH
 * @since 2022/2/19
 */
public interface EmallUserMapper extends BaseMapper<EmallUser> {
    
    EmallUser selectByOpenId(String openId);
}