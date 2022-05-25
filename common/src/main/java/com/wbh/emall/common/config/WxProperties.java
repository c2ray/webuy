package com.wbh.emall.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 小程序配置文件
 *
 * @author WBH
 * @since 2022/2/13
 */
@Data
@ConfigurationProperties(prefix = "wx.miniapp")
public class WxProperties {
    /**
     * 设置微信小程序的appid
     */
    private String appid;
    
    /**
     * 设置微信小程序的Secret
     */
    private String secret;
    
    /**
     * 设置微信小程序消息服务器配置的token
     */
    private String token;
    
    /**
     * 设置微信小程序消息服务器配置的EncodingAESKey
     */
    private String aesKey;
    
    /**
     * 消息格式，XML或者JSON
     */
    private String msgDataFormat;
}
