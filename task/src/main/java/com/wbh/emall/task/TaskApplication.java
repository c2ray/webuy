package com.wbh.emall.task;

import com.wbh.emall.common.config.Swagger2Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = {"com.wbh.emall"})
@Import({Swagger2Configuration.class})
public class TaskApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);
    }
    
}
