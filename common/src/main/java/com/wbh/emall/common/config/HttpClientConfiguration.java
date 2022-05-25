package com.wbh.emall.common.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author WBH
 * @since 2022/3/25
 */
@Configuration
public class HttpClientConfiguration {
    
    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }

}
