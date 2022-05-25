package com.wbh.emall.common.util;

import com.wbh.emall.common.exception.AuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.HexUtils;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WBH
 * @since 2022/2/14
 */
@Slf4j
public class EncryptUtil {
    
    
    /**
     * 密钥字符串
     */
    private static final String SECRET_KEY = "53AA762A10C4741758C57FE1AF6E5EA0916EDEIK";
    
    /**
     * jwt密钥
     */
    private static final byte[] JWT_KEY = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
    
    /**
     * 加密算法
     */
    private static final String ALGORITHM = "Blowfish/ECB/PKCS5Padding";
    /**
     * 密钥
     */
    private static final SecretKeySpec KEY = new SecretKeySpec(SECRET_KEY.getBytes(), "Blowfish");
    private static Cipher CIPHER = null;
    
    static {
        try {
            CIPHER = Cipher.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            log.error("初始化加密工具类失败", e);
        }
    }
    
    /**
     * blowfish加密
     *
     * @param content 待加密内容
     * @return 加密后的密文
     */
    public static String blowfishEncrypt(String content) throws GeneralSecurityException {
        CIPHER.init(Cipher.ENCRYPT_MODE, KEY);
        byte[] encryptBytes = CIPHER.doFinal(content.getBytes());
        // 推荐采用十六进制转换的方式使得字符串能被更好地兼容, 如果直接转String可能不能被正确解析
        return HexUtils.toHexString(encryptBytes);
    }
    
    /**
     * blowfish解密
     *
     * @param chiperText 密文
     * @return 解密后的明文
     */
    @Deprecated
    public static String blowfishDecrypt(String chiperText) throws GeneralSecurityException {
        CIPHER.init(Cipher.DECRYPT_MODE, KEY);
        return new String(CIPHER.doFinal(HexUtils.fromHexString(chiperText)));
    }
    
    /**
     * 使用blowfish加密id
     *
     * @param id 待加密的id
     * @return 加密后的id
     */
    public static String encryptId(Integer id) {
        HashMap<String, Object> claim = new HashMap<>();
        claim.put("id", id);
        // 获取当前时间戳
        long now = System.currentTimeMillis();
        long expire = now + 24 * 3600 * 1000;
        claim.put("expire", expire);
        return generateToken(claim);
    }
    
    /**
     * 等待解密的token
     *
     * @param token 待解密的token
     * @return 从token中解密获取的id
     * @throws AuthenticationException token解密失败
     */
    public static int decryptId(String token) throws AuthenticationException {
        try {
            Claims claims = decodeJwtToken(token);
            return claims.get("id", Integer.class);
        } catch (Exception e) {
            throw new AuthenticationException("尚未登录!", e);
        }
    }
    
    /**
     * jwt解密token
     */
    public static Claims decodeJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(JWT_KEY).build()
                .parseClaimsJws(token).getBody();
    }
    
    /**
     * 生成jwt token
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(Keys.hmacShaKeyFor(JWT_KEY))
                .compact();
    }
    
    
    /**
     * 生成token的过期时间
     */
    private static Date generateExpirationDate() {
        // return new Date(System.currentTimeMillis() + expiration * 1000);
        return null;
    }
    
    public static void main(String[] args) {
        HashMap<String, Object> claim = new HashMap<>();
        claim.put("id", "123");
        String jwtEncrypt = generateToken(claim);
        Claims claims = decodeJwtToken(jwtEncrypt);
        System.out.println(claims.get("id"));
        System.out.println(jwtEncrypt);
        System.out.println(claims);
    }
    
    
}
