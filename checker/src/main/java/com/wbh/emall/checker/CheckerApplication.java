package com.wbh.emall.checker;

import com.wbh.emall.common.config.WxConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync(proxyTargetClass = true)
@EnableCaching(proxyTargetClass = true)
@SpringBootApplication(scanBasePackages = {"com.wbh.emall"})
@Import({WxConfiguration.class})
public class CheckerApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(CheckerApplication.class, args);
        // SpringApplication springApplication = new SpringApplication(CheckerApplication.class);
        // springApplication.setWebApplicationType(WebApplicationType.NONE);
        // springApplication.run(args);
    }
    
}
