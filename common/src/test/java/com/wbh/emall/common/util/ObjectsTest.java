package com.wbh.emall.common.util;


import com.wbh.emall.common.entity.Result;
import org.junit.jupiter.api.Test;

/**
 * @author WBH
 * @since 2022/2/27
 */
class ObjectsTest {
    
    @Test
    void hasFieldsFilled() throws IllegalAccessException {
        Result<String> success = Result.success("ii", null);
        boolean b = Objects.assertFieldsFilled(success, "msg");
        System.out.println(b);
    }
}