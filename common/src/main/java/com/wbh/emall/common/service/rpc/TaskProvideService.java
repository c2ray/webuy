package com.wbh.emall.common.service.rpc;

import com.wbh.emall.common.entity.rpc.PicCheckerDto;

/**
 * @author WBH
 * @since 2022/3/25
 */
public interface TaskProvideService {
    
    /**
     * 添加图片检测任务
     */
    void addPicCheckTask(PicCheckerDto picCheckerDto);
    
}
