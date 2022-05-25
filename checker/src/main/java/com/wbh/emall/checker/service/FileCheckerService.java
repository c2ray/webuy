package com.wbh.emall.checker.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import com.alibaba.fastjson.JSON;
import com.wbh.emall.common.constants.GlobalConstant;
import com.wbh.emall.common.entity.rpc.PicCheckerDto;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author WBH
 * @since 2022/3/24
 */
@Service
@Slf4j
public class FileCheckerService {
    
    public static final String UPDATE_PIC_CHECK_URL = GlobalConstant.PREFIX_URL + "/api/euser/goods/pic";
    
    final OkHttpClient client;
    
    final WxMaService maService;
    
    public FileCheckerService(OkHttpClient client, WxMaService maService) {
        this.client = client;
        this.maService = maService;
    }
    
    public Boolean checkImg(PicCheckerDto picCheckerDto) throws WxErrorException, IOException {
        log.info("开始审核: {}", picCheckerDto);
        boolean legal = maService.getSecCheckService().checkImage(picCheckerDto.getImgUrl());
        if (legal) {
            picCheckerDto.setLegal(1);
        } else {
            picCheckerDto.setLegal(-1);
        }
        String picCheckDtoJson = JSON.toJSONString(picCheckerDto);
        okhttp3.RequestBody reqBody = okhttp3.RequestBody.create(MediaType.get("application/json"), picCheckDtoJson);
        Request request = new Request.Builder().url(UPDATE_PIC_CHECK_URL).put(reqBody).build();
        Call call = client.newCall(request);
        call.execute();
        return legal;
    }
    
}
