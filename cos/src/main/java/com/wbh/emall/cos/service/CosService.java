package com.wbh.emall.cos.service;

import com.qcloud.cos.exception.CosClientException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author WBH
 */
public interface CosService {
    //上传文件
    String uploadImage(MultipartFile file) throws IOException;
    
    String uploadBase64(String base64) throws CosClientException, IOException;
}
