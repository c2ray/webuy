package com.wbh.emall.cos;

import com.wbh.emall.common.config.Swagger2Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author WBH
 */
@SpringBootApplication(scanBasePackages = {"com.wbh.emall"})
@Import(Swagger2Configuration.class)
public class CosApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(CosApplication.class, args);
    }
    
}
