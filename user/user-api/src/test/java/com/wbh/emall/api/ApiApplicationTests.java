package com.wbh.emall.api;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.wbh.emall.common.util.EncryptUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.Random;

@SpringBootTest
@EnableDiscoveryClient(autoRegister = false)
class ApiApplicationTests {
    // @Autowired
    // IdentifierGenerator identifierGenerator;
    
    @Test
    void testIdGenerator() throws InterruptedException {
        Thread.sleep(20000);
        String name = ApiApplicationTests.class.getName();
        for (int i = 0; i < 100000; i++) {
            // String s = identifierGenerator.nextUUID(name);
            // Number number = identifierGenerator.nextId(name);
            // System.out.println(number);
        }
    }
    
    @Test
    void testEncrypt() {
        try {
            String keyStr = "53aa762a10c4741758c57fe1af6e5ea0916edeik";
            String content = "123";
            String s = EncryptUtil.blowfishEncrypt(content);
            System.out.println(s);
            s = EncryptUtil.blowfishDecrypt(s);
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    void testRandom() {
        Random random = new Random(1);
        random.setSeed(10);
        System.out.println(random.nextInt());
        random.setSeed(10);
        System.out.println(random.nextInt());
        random.setSeed(10);
        System.out.println(random.nextInt());
    }
    
}
