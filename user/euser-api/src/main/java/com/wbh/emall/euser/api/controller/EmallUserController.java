package com.wbh.emall.euser.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wbh.emall.common.entity.Result;
import com.wbh.emall.common.exception.AuthenticationException;
import com.wbh.emall.common.util.EncryptUtil;
import com.wbh.emall.user.common.entity.EmallUser;
import com.wbh.emall.user.common.entity.vo.AvatarVo;
import com.wbh.emall.user.common.entity.vo.EmallUserVo;
import com.wbh.emall.user.common.entity.vo.PasswordVo;
import com.wbh.emall.user.common.service.EmallUserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.GeneralSecurityException;

/**
 * emall网页控制器
 *
 * @author WBH
 * @since 2022/2/27
 */
@RestController
@RequestMapping("/api/euser")
@Slf4j
@CrossOrigin
public class EmallUserController {
    
    private final EmallUserService emallUserService;
    
    public EmallUserController(EmallUserService emallUserService) {
        this.emallUserService = emallUserService;
    }
    
    /**
     * 用户登录并获取信息
     */
    @ApiOperation("用户登录")
    @PostMapping("/session")
    public Result<EmallUserVo> login(@RequestBody EmallUser user) throws GeneralSecurityException,
                                                                         AuthenticationException {
        String username = user.getUsername();
        String password = EncryptUtil.blowfishEncrypt(user.getPassword());
        
        log.info("用户发起登录: {}", user);
        log.info("password = {}", password);
        // mybatis-plus 的查询要用 new
        user = emallUserService.getOne(new QueryWrapper<EmallUser>().select("id", "avatar_url")
                .eq("username", username)
                .eq("password", password));
        if (user != null) {
            EmallUserVo emallUserVo = new EmallUserVo();
            emallUserVo.setUsername(username);
            emallUserVo.setAvatarUrl(user.getAvatarUrl());
            emallUserVo.setToken(EncryptUtil.encryptId(user.getId()));
            log.info("用户登录成功! {}", emallUserVo);
            return Result.success(emallUserVo);
        } else {
            throw new AuthenticationException("用户名或密码错误!");
        }
    }
    
    /**
     * 修改密码
     */
    @ApiOperation("修改密码")
    @PutMapping("/password")
    public Result<String> changePassword(@RequestHeader("Authorization") String token,
                                         @RequestBody PasswordVo passwordVo) throws AuthenticationException,
                                                                                    GeneralSecurityException {
        log.info("passwordVo={}", passwordVo);
        int id = EncryptUtil.decryptId(token);
        String oldPassword = EncryptUtil.blowfishEncrypt(passwordVo.getOldPassword());
        EmallUser user = new EmallUser();
        user.setPassword(EncryptUtil.blowfishEncrypt(passwordVo.getNewPassword()));
        boolean isSuccess = emallUserService.update(user, new QueryWrapper<EmallUser>()
                .eq("id", id)
                .eq("password", oldPassword));
        
        if (isSuccess) {
            return Result.success(null, "修改成功");
        } else {
            throw new AuthenticationException("密码错误!");
        }
    }
    
    /**
     * 修改头像
     */
    @ApiOperation("修改头像")
    @PutMapping("/avatar")
    public Result<String> changeAvatar(@RequestHeader("Authorization") String token,
                                       @RequestBody AvatarVo avatarVo) throws AuthenticationException {
        int id = EncryptUtil.decryptId(token);
        String url = avatarVo.getUrl();
        log.info("用户: {} 修改头像为: {}", id, url);
        boolean isSuccess = emallUserService.update()
                .eq("id", id)
                .set("avatar_url", url).update();
        if (isSuccess) {
            return Result.success(null, "修改成功");
        } else {
            throw new AuthenticationException("认证失败!");
        }
    }
    
}
