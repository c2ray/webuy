package com.wbh.emall.euser.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wbh.emall.common.entity.rpc.PicCheckerDto;
import com.wbh.emall.user.common.entity.EmallGoodsPic;
import com.wbh.emall.user.common.service.EmallGoodsPicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author WBH
 * @since 2022/3/25
 */
@RestController
@RequestMapping("/api/euser/goods/pic")
@Slf4j
public class EmallGoodsPicController {
    
    @Resource
    EmallGoodsPicService emallGoodsPicService;
    
    @PutMapping
    public void updateEmallGoodsPic(@RequestBody PicCheckerDto picCheckerDto) {
        log.info("更新数据: {}", picCheckerDto);
        EmallGoodsPic emallGoodsPic = new EmallGoodsPic();
        emallGoodsPic.setLegal(picCheckerDto.getLegal());
        emallGoodsPicService.update(emallGoodsPic, new QueryWrapper<EmallGoodsPic>().eq("id", picCheckerDto.getPicId()));
    }
}
