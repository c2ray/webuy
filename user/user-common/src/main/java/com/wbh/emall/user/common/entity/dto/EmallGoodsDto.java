package com.wbh.emall.user.common.entity.dto;

import com.wbh.emall.user.common.entity.EmallGoods;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author WBH
 * @since 2022/3/13
 */

@EqualsAndHashCode(callSuper = true)
@ToString
@Data
public class EmallGoodsDto extends EmallGoods {
    private String coverImg;
    
    private List<String> imgs;

}

