package com.wbh.emall.task.service;

import com.wbh.emall.common.constants.TaskConstants;
import com.wbh.emall.common.entity.rpc.PicCheckerDto;
import com.wbh.emall.common.service.rpc.TaskProvideService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.StreamOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author WBH
 * @since 2022/3/24
 */
@Service
@Slf4j
public class TaskProvideServiceImpl implements TaskProvideService {
    
    private final StringRedisTemplate redisTemplate;
    
    public TaskProvideServiceImpl(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    
    
    /**
     * 向redis添加任务数据
     */
    @Override
    public void addPicCheckTask(PicCheckerDto picCheckerDto) {
        log.info("添加图片检测任务：{}", picCheckerDto);
        ObjectRecord<String, PicCheckerDto> cheker = StreamRecords.newRecord()
                .ofObject(picCheckerDto).withStreamKey(TaskConstants.PIC_CHECK_STREAM_KEY);
        StreamOperations<String, Object, Object> streamOps = redisTemplate.opsForStream();
        streamOps.add(cheker);
    }
}
