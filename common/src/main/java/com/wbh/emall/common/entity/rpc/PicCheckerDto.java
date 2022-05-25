package com.wbh.emall.common.entity.rpc;

import lombok.Data;

import java.util.Date;

/**
 * @author WBH
 * @since 2022/3/24
 */
@Data
public class PicCheckerDto {
    /**
     * 待检测的图片id
     */
    Integer picId;
    
    /**
     * 图片url
     */
    String imgUrl;
    
    /**
     * 合法状态
     */
    Integer legal;
    
}
