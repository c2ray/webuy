package com.wbh.emall.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;

/**
 * @author WBH
 * @since 2022/2/23
 */
@Configuration
public class EmallWebMvcConfiguration implements WebMvcConfigurer {
    
    /**
     * 配置编码解析器
     */
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(StandardCharsets.UTF_8);
    }
}
