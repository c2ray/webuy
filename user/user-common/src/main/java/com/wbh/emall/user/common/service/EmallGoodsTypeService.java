package com.wbh.emall.user.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wbh.emall.user.common.entity.EmallGoodsType;
import com.wbh.emall.user.common.mapper.EmallGoodsTypeMapper;
import org.springframework.stereotype.Service;

/**
 * @author WBH
 * @since 2022/2/20
 */
@Service
public class EmallGoodsTypeService extends ServiceImpl<EmallGoodsTypeMapper, EmallGoodsType> implements IService<EmallGoodsType> {
    
}


