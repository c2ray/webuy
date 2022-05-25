package com.wbh.emall.checker.message;


import com.wbh.emall.checker.service.FileCheckerService;
import com.wbh.emall.common.constants.TaskConstants;
import com.wbh.emall.common.entity.rpc.PicCheckerDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.core.StreamOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.hash.BeanUtilsHashMapper;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.locks.Lock;

/**
 * @author WBH
 * @since 2021/12/1
 */
@Slf4j
public class CheckTaskStreamListener implements StreamListener<String, MapRecord<String, String, String>> {
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    @Autowired
    private RedisLockRegistry redisLockRegistry;
    
    @Autowired
    private FileCheckerService fileCheckerService;
    
    @SneakyThrows
    @Override
    @Async("checkExecutor")
    public void onMessage(MapRecord<String, String, String> message) {
        StreamOperations<String, Object, Object> streamOps = redisTemplate.opsForStream();
        ObjectRecord<String, PicCheckerDto> checkTaskRecord =
                message.toObjectRecord(new BeanUtilsHashMapper<>(PicCheckerDto.class));
        PicCheckerDto checkTask = checkTaskRecord.getValue();
        String url = checkTask.getImgUrl();
        int id = checkTask.getPicId();
        RecordId rid = message.getId();
        // 分布式锁
        Lock lock = redisLockRegistry.obtain(rid.getValue());
        if (lock.tryLock()) {
            try {
                fileCheckerService.checkImg(checkTask);
                streamOps.delete(TaskConstants.PIC_CHECK_STREAM_KEY, rid);
            } finally {
                lock.unlock();
            }
        }
    }
    
    
}
