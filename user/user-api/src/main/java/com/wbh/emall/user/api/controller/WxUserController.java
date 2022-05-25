package com.wbh.emall.user.api.controller;


import com.wbh.emall.common.entity.LoginResult;
import com.wbh.emall.common.entity.Result;
import com.wbh.emall.common.exception.AuthenticationException;
import com.wbh.emall.common.exception.EmallException;
import com.wbh.emall.common.util.EncryptUtil;
import com.wbh.emall.common.util.Objects;
import com.wbh.emall.user.common.entity.EmallUser;
import com.wbh.emall.user.common.entity.UserInfo;
import com.wbh.emall.user.common.service.EmallUserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.security.GeneralSecurityException;

/**
 * 微信小程序用户控制器
 *
 * @author WBH
 * @since 2022/2/16
 */
@Slf4j
@RequestMapping("/api/wx/user")
@RestController
public class WxUserController {
    
    private final EmallUserService emallUserService;
    
    public WxUserController(EmallUserService emallUserService) {
        this.emallUserService = emallUserService;
    }
    
    @ApiOperation("修改用户信息")
    @PutMapping
    public Result<Boolean> updateUser(@RequestHeader("Authorization") String token,
                                      @RequestBody EmallUser user) throws AuthenticationException {
        Assert.isTrue(Objects.hasFieldFilled(user), "参数不能为空!");
        int id = EncryptUtil.decryptId(token);
        user.setId(id);
        log.info("更新用户: {}", user);
        return Result.success(emallUserService.updateUser(user) > 0);
    }
    
    
    /**
     * 用户登录并获取信息
     */
    @ApiOperation("用户登录")
    @PostMapping
    public LoginResult<EmallUser> login(@RequestBody UserInfo userInfo) throws GeneralSecurityException,
                                                                               WxErrorException {
        String code = userInfo.getCode();
        if (StringUtils.isBlank(code)) {
            throw new IllegalArgumentException("code 为空!");
        }
        // 获取用户信息, 如果用户不存在则创建, 如果用户存在就获取用户信息
        EmallUser user = emallUserService.getUser(userInfo);
        Integer uid = user.getId();
        String token = EncryptUtil.encryptId(uid);
        log.info("用户登录成功: user={}, token={}", user, token);
        return LoginResult.success(user, token);
    }
    
    /**
     * 用户登录并获取信息
     */
    @ApiOperation("注册企业用户")
    @PutMapping("/business")
    public Result<EmallUser> registerBusiness(@RequestHeader("Authorization") String token,
                                              @RequestBody EmallUser emallUser) throws AuthenticationException, EmallException, GeneralSecurityException, IllegalAccessException {
        Objects.assertFieldsFilled(emallUser,
                EmallUser.Fields.username,
                EmallUser.Fields.password,
                EmallUser.Fields.trueName,
                EmallUser.Fields.idCard);
        int id = EncryptUtil.decryptId(token);
        // 加密密码
        emallUser.setPassword(EncryptUtil.blowfishEncrypt(emallUser.getPassword()));
        emallUser.setId(id);
        try {
            emallUserService.updateById(emallUser);
        } catch (DuplicateKeyException e) {
            throw new EmallException("用户名已存在!");
        }
        return Result.success(null);
    }
    
}
