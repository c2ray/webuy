package com.wbh.emall.user.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wbh.emall.user.common.entity.EmallGoodsCover;
import com.wbh.emall.user.common.mapper.EmallGoodsCoverMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WBH
 * @since 2022/2/23
 */
@Service
public class
EmallGoodsCoverService extends ServiceImpl<EmallGoodsCoverMapper, EmallGoodsCover> implements IService<EmallGoodsCover> {
    
    private final com.wbh.emall.user.common.mapper.EmallGoodsCoverMapper emallGoodsCoverMapper;
    
    public EmallGoodsCoverService(com.wbh.emall.user.common.mapper.EmallGoodsCoverMapper emallGoodsCoverMapper) {
        this.emallGoodsCoverMapper = emallGoodsCoverMapper;
    }
    
    public List<com.wbh.emall.user.common.entity.vo.EmallGoodsVo> selectRandomGoodsCover(Integer num) {
        return emallGoodsCoverMapper.selectRandomGoodsCover(num);
    }
}
