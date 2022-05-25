package com.wbh.emall.checker.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author WBH
 * @since 2022/3/24
 */
@Slf4j
public class FileChecker {
    
    /**
     * 检测文件扩展名是否合法
     *
     * @param urlStr 文件的url地址
     * @return 文件是否合法
     */
    public static boolean isFileTypeLegal(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        String title = url.getFile();
        InputStream inputStream = url.openStream();
        return isFileTypeLegal(title, inputStream);
    }
    
    
    /**
     * 检测文件扩展名是否非法
     *
     * @param inputStream 文件的输入流
     * @param title       文件名称
     * @return 文件类型是否合法
     */
    public static boolean isFileTypeLegal(String title, InputStream inputStream) throws IOException {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)) {
            bufferedInputStream.mark(0);
            String extension = getExternsion(title);
            byte[] fileHeaderBytes = new byte[10];
            inputStream.read(fileHeaderBytes);
            String fileHeader = bytesToHexString(fileHeaderBytes);
            log.info("文件头为{}", fileHeader);
            assert fileHeader != null;
            boolean isLegal = false;
            switch (extension.toLowerCase()) {
                case "pdf":
                    isLegal = fileHeader.startsWith("255044462D312E");
                    break;
                case "jpg":
                case "jpeg":
                case "gif":
                case "png":
                case "bmp":
                    isLegal = fileHeader.startsWith("FFD8FF") || fileHeader.startsWith("47494638")
                            || fileHeader.startsWith("424D") || fileHeader.startsWith("89504E47");
                    break;
                // 如果文件不在此列表中, 就返回false
                default:
                    log.error("{} 该文件未经过审查, 扩展名为{}, 文件头为{}", title, extension, fileHeader);
            }
            // 如果文件非法, 输出文件信息
            if (!isLegal) {
                log.error("{} 文件可能非法, 文件头为{}", title, fileHeader);
            }
            bufferedInputStream.reset();
            return isLegal;
        }
    }
    
    
    /**
     * 获取文件的扩展名, 返回扩展名不带.号
     *
     * @param name 文件名
     * @return 文件的扩展名
     */
    public static String getExternsion(String name) {
        int len = name.length();
        return name.substring(name.lastIndexOf(".") + 1, len);
    }
    
    
    /**
     * Convert byte[] to hex string.这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。
     *
     * @param src byte[] data
     * @return hex string
     */
    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte b : src) {
            int v = b & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString().toUpperCase();
    }
    
    
}
