package com.wbh.emall.user.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wbh.emall.user.common.entity.EmallGoodsPic;
import com.wbh.emall.user.common.entity.vo.EmallGoodsPicVo;
import com.wbh.emall.user.common.mapper.EmallGoodsPicMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WBH
 * @since 2022/2/20
 */
@Service
public class EmallGoodsPicService extends ServiceImpl<EmallGoodsPicMapper, EmallGoodsPic> implements IService<EmallGoodsPic> {
    private final EmallGoodsPicMapper emallGoodsPicMapper;
    
    public EmallGoodsPicService(EmallGoodsPicMapper emallGoodsPicMapper) {
        this.emallGoodsPicMapper = emallGoodsPicMapper;
    }
    
    public List<EmallGoodsPicVo> selectPicListByGid(Integer gid) {
        return emallGoodsPicMapper.selectPicListByGid(gid);
    }
    
}


