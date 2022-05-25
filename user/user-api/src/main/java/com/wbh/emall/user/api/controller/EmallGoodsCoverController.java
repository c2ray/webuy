package com.wbh.emall.user.api.controller;

import com.wbh.emall.common.entity.Result;
import com.wbh.emall.user.common.entity.vo.EmallGoodsVo;
import com.wbh.emall.user.common.service.EmallGoodsCoverService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author WBH
 * @since 2022/2/24
 */
@RestController
@RequestMapping("/api/goods/cover")
public class EmallGoodsCoverController {
    private final EmallGoodsCoverService emallGoodsCoverService;
    
    public EmallGoodsCoverController(EmallGoodsCoverService emallGoodsCoverService) {
        this.emallGoodsCoverService = emallGoodsCoverService;
    }
    
    /**
     * 随机返回商品封面
     *
     * @return 商品封面信息
     */
    @GetMapping("/random")
    Result<List<EmallGoodsVo>> getRandomCover() {
        return Result.success(emallGoodsCoverService.selectRandomGoodsCover(3));
    }
    
}
