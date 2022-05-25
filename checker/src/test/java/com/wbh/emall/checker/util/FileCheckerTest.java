package com.wbh.emall.checker.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;

/**
 * @author WBH
 * @since 2022/3/24
 */
class FileCheckerTest {
    
    @ParameterizedTest
    @ValueSource(strings = {
            "https://emall-1258606498.cos.ap-beijing.myqcloud.com/2022/test1.png",
            "https://emall-1258606498.cos.ap-beijing.myqcloud.com/2022/test2.jpg",
            "https://emall-1258606498.cos.ap-beijing.myqcloud.com/2022/test3.jpg"
    })
    public void isFileTypeLegal(String urlStr) throws IOException {
        assert FileChecker.isFileTypeLegal(urlStr);
    }
    
}