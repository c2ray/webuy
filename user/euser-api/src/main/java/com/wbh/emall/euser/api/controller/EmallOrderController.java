package com.wbh.emall.euser.api.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wbh.emall.common.entity.Result;
import com.wbh.emall.common.exception.AuthenticationException;
import com.wbh.emall.common.util.EncryptUtil;
import com.wbh.emall.user.common.entity.vo.EmalEUserOrderVo;
import com.wbh.emall.user.common.service.EmallOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WBH
 * @since 2022/3/23
 */
@RestController
@RequestMapping("/api/euser/order")
public class EmallOrderController {
    
    final EmallOrderService orderService;
    
    public EmallOrderController(EmallOrderService orderService) {
        this.orderService = orderService;
    }
    
    @GetMapping("/list")
    @ApiOperation("获取商家订单信息")
    public Result<IPage<EmalEUserOrderVo>> changeAvatar(@RequestHeader("Authorization") String token,
                                                        Integer size, Integer current) throws AuthenticationException {
        Integer uid = EncryptUtil.decryptId(token);
        return Result.success(orderService.getOrderByEUserid(uid, current, size));
    }
}
