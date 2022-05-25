package com.wbh.emall.euser.api;

import com.wbh.emall.common.config.Swagger2Configuration;
import com.wbh.emall.common.config.WxConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@SpringBootApplication(scanBasePackages = {"com.wbh.emall"})
@Import({Swagger2Configuration.class, WxConfiguration.class})
public class BusinessApiApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(BusinessApiApplication.class, args);
    }
    
}
