package com.wbh.emall.cos.controller;

import com.qcloud.cos.exception.CosClientException;
import com.wbh.emall.common.entity.Result;
import com.wbh.emall.common.exception.AuthenticationException;
import com.wbh.emall.common.exception.NetworkException;
import com.wbh.emall.common.util.EncryptUtil;
import com.wbh.emall.cos.entity.Base64ToMultipartFile;
import com.wbh.emall.cos.entity.Base64Vo;
import com.wbh.emall.cos.service.CosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author WBH
 * @since 2022/2/21
 */
@Slf4j
@RequestMapping("/api/cos")
@RestController
public class CosController {
    
    private final CosService cosService;
    
    public CosController(CosService cosService) {
        this.cosService = cosService;
    }
    
    /**
     * 上传图片文件
     *
     * @param token 用户认证token
     * @param file  文件的内容
     * @return 文件在cos的路径
     * @throws AuthenticationException 用户认证异常
     * @throws NetworkException        上传腾讯云时出现的网络异常
     */
    @PostMapping("/image")
    public Result<String> uploadImage(@RequestHeader("Authorization") String token, MultipartFile file) throws AuthenticationException, NetworkException {
        // 仅登录的用户可上传
        EncryptUtil.decryptId(token);
        //返回上传到cos的路径
        String url;
        try {
            url = cosService.uploadImage(file);
            log.info("上传文件: file={}, url={}", file.getOriginalFilename(), url);
            return Result.success(url, "上传成功!");
        } catch (Exception e) {
            log.error("上传文件失败: file={}", file);
            throw new NetworkException("网络异常!", e);
        }
    }
    
    
    /**
     * 上传base64图片
     *
     * @param token    用户认证token
     * @param base64Vo base64字符串
     * @return 文件在cos的路径
     * @throws AuthenticationException 用户认证异常
     * @throws NetworkException        上传腾讯云时出现的网络异常
     */
    @PostMapping("/base64")
    public Result<String> uploadBase64(@RequestHeader("Authorization") String token,
                                       @RequestBody Base64Vo base64Vo) throws AuthenticationException,
                                                                              NetworkException {
        // 仅登录的用户可上传
        EncryptUtil.decryptId(token);
        String url;
        try {
            url = cosService.uploadBase64(base64Vo.getFile());
            log.info("上传文件: url={}", url);
            return Result.success(url, "上传成功!");
        } catch (Exception e) {
            log.error("上传文件失败: file={}", base64Vo);
            throw new NetworkException("网络异常!", e);
        }
    }
}
