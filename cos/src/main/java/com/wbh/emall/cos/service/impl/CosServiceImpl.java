package com.wbh.emall.cos.service.impl;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.model.ObjectMetadata;
import com.wbh.emall.cos.config.CosProperties;
import com.wbh.emall.cos.service.CosService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageInputStreamImpl;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Objects;
import java.util.UUID;

/**
 * @author WBH
 */
@Service
public class CosServiceImpl implements CosService {
    
    private final COSClient cosClient;
    
    private final CosProperties cosProperties;
    
    public CosServiceImpl(COSClient cosClient, CosProperties cosProperties) {
        this.cosClient = cosClient;
        this.cosProperties = cosProperties;
    }
    
    //上传头像到oss
    @Override
    public String uploadImage(MultipartFile file) throws CosClientException, IOException {
        InputStream inputStream = file.getInputStream();
        String extension = Objects.requireNonNull(file.getContentType()).split("/")[1];
        String bucketName = cosProperties.getBucketName();
        String endpoint = cosProperties.getEndpoint();
        // 在文件名称里面添加随机唯一的值
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        // yuy76t5rew01.jpg
        String fileName = uuid + "." + extension;
        // 把文件按照日期进行分类
        // 2022/02/21/
        String datePath = new DateTime().toString("yyyy/MM/dd");
        // 2022/02/21/ewtqr313401.jpg
        fileName = datePath + "/" + fileName;
        // 配置文件元数据
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());
        cosClient.putObject(bucketName, fileName, inputStream, objectMetadata);
        // https://emall-1258606498.cos.ap-beijing.myqcloud.com/2022/02/21/6c56d43301a843cc89a4ab3ffeb12f5cgit.png
        return "https://" + bucketName + "." + endpoint + "/" + fileName;
    }
    
    
    /**
     * 保存base64字符串为图片
     */
    @Override
    public String uploadBase64(String base64) throws CosClientException, IOException {
        base64 = processBase64(base64);
        byte[] bytes = Base64.getDecoder().decode(base64);
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes)) {
            String extension = ".png";
            String bucketName = cosProperties.getBucketName();
            String endpoint = cosProperties.getEndpoint();
            // 在文件名称里面添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            // yuy76t5rew.png
            String fileName = uuid + extension;
            // 把文件按照日期进行分类
            // 2022/02/21/
            String datePath = new DateTime().toString("yyyy/MM/dd");
            // 2022/02/21/ewtqr.png
            fileName = datePath + "/" + fileName;
            // 配置文件元数据
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(byteArrayInputStream.available());
            objectMetadata.setContentType("application/x-png");
            cosClient.putObject(bucketName, fileName, byteArrayInputStream, objectMetadata);
            // https://emall-1258606498.cos.ap-beijing.myqcloud.com/2022/02/21/6c56d43301a843cc89a4ab3ffeb12f5cgit.png
            return "https://" + bucketName + "." + endpoint + "/" + fileName;
        }
    }
    
    /**
     * 处理base64字符串
     *
     * @param base64 待处理的base64字符串
     * @return 处理过的base64字符串
     */
    public static String processBase64(String base64) {
        if (base64.contains("data:")) {
            int start = base64.indexOf(",");
            base64 = base64.substring(start + 1);
        }
        return base64.replaceAll("[\r\n]", "");
    }
    
}
