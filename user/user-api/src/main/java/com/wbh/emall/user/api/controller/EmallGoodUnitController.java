package com.wbh.emall.user.api.controller;

import com.wbh.emall.common.entity.Result;
import com.wbh.emall.user.common.entity.EmallGoodsUnit;
import com.wbh.emall.user.common.service.EmallGoodsUnitService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author WBH
 * @since 2022/3/11
 */
@RestController
@RequestMapping("/api/goods/unit")
public class EmallGoodUnitController {
    /**
     * 服务对象
     */
    @Resource
    private EmallGoodsUnitService emallGoodsUnitService;
    
    
    @ApiOperation("获取商品单位")
    @GetMapping("/list")
    public Result<List<EmallGoodsUnit>> getGoodsUnits() {
        List<EmallGoodsUnit> goodsUnits = emallGoodsUnitService.query()
                .select("id", "name").list();
        return Result.success(goodsUnits);
    }
    
}
