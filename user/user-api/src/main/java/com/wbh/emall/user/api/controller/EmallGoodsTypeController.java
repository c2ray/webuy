package com.wbh.emall.user.api.controller;

import com.wbh.emall.common.entity.Result;
import com.wbh.emall.user.common.entity.EmallGoodsType;
import com.wbh.emall.user.common.service.EmallGoodsTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (emall_goods_type)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/api/goods/type")
public class EmallGoodsTypeController {
    /**
     * 服务对象
     */
    @Resource
    private EmallGoodsTypeService emallGoodsTypeService;
    
    
    @ApiOperation("获取所有商品类型数据")
    @GetMapping("/list")
    public Result<List<EmallGoodsType>> getAllGoodsTypes() {
        List<EmallGoodsType> goodsTypes = emallGoodsTypeService.query()
                .select("id", "name").list();
        return Result.success(goodsTypes);
    }
    
    
    @ApiOperation("添加商品类型")
    @PostMapping
    public Result<Boolean> addGoodType(@RequestBody EmallGoodsType emallGoodsType) {
        return Result.success(emallGoodsTypeService.save(emallGoodsType));
    }
    
}
