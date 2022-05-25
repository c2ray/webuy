package com.wbh.emall.cos.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 腾讯云cos配置
 *
 * @author WBH
 * @since 2022/2/21
 */
@Data
@ConfigurationProperties(prefix = "tx.cos")
public class CosProperties {
    /**
     * 腾讯云appId
     */
    private String appId;
    /**
     * 腾讯云密钥Id
     */
    private String secretId;
    /**
     * 腾讯云密钥
     */
    private String secretKey;
    /**
     * 存储桶地区
     */
    private String region;
    
    /**
     * 存储桶名称
     */
    String bucketName;
    
    String endpoint;
    
}
