package com.wbh.emall.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author WBH
 */
@SpringBootApplication(scanBasePackages = "com.wbh.emall")
public class GatewayApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
    
}
