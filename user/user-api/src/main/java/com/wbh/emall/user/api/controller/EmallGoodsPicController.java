package com.wbh.emall.user.api.controller;

import com.wbh.emall.user.common.entity.EmallGoodsPic;
import com.wbh.emall.user.common.service.EmallGoodsPicService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (emall_goods_pic)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/api/goods/pic")
public class EmallGoodsPicController {
    /**
     * 服务对象
     */
    @Resource
    private EmallGoodsPicService emallGoodsPicService;
    
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("通过主键查询单条数据")
    @GetMapping
    public EmallGoodsPic selectOne(Integer id) {
        return emallGoodsPicService.getById(id);
    }
    
    @ApiOperation("添加商品图片")
    @PostMapping
    public boolean insert(EmallGoodsPic emallGoodsPic) {
        return emallGoodsPicService.save(emallGoodsPic);
    }
}
