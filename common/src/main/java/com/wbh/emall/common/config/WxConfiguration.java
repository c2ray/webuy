package com.wbh.emall.common.config;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author WBH
 * @since 2022/2/13
 */
@Slf4j
@EnableConfigurationProperties(WxProperties.class)
public class WxConfiguration {
    
    private final WxProperties wxProperties;
    
    private final WxMaService wxMaService = new WxMaServiceImpl();
    
    public WxConfiguration(WxProperties wxProperties) {
        this.wxProperties = wxProperties;
    }
    
    @Bean
    public WxMaService getWxMaService() {
        return wxMaService;
    }
    
    /**
     * 将配置文件中的配置拷贝到WxMaDefaultConfigImpl中
     */
    @PostConstruct
    public void init() {
        WxMaDefaultConfigImpl wxMaDefaultConfig = new WxMaDefaultConfigImpl();
        BeanUtils.copyProperties(wxProperties, wxMaDefaultConfig);
        wxMaService.setWxMaConfig(wxMaDefaultConfig);
    }
    
    
}
