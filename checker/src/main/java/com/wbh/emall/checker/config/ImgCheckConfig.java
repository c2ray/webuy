package com.wbh.emall.checker.config;

import com.wbh.emall.checker.message.CheckTaskStreamListener;
import com.wbh.emall.common.constants.TaskConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;
import org.springframework.integration.redis.util.RedisLockRegistry;

import java.util.concurrent.Executor;

/**
 * 图片检测配置
 *
 * @author WBH
 * @since 2022/3/24
 */
@Configuration(proxyBeanMethods = false)
public class ImgCheckConfig {
    
    
    /**
     * redis模板
     */
    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }
    
    @Bean
    RedisLockRegistry redisLockRegistry(RedisConnectionFactory connectionFactory) {
        // 设置锁在10分钟后过期
        return new RedisLockRegistry(connectionFactory, TaskConstants.PIC_CHECK_LOCK, 600_000);
    }
    
    /**
     * redis stream 订阅
     */
    @Bean
    Subscription subscription(RedisConnectionFactory connectionFactory,
                              CheckTaskStreamListener checkTaskStreamListener,
                              Executor checkExecutor) {
        // 多线程执行
        StreamMessageListenerContainer
                .StreamMessageListenerContainerOptions<String, MapRecord<String, String, String>> options
                = StreamMessageListenerContainer.StreamMessageListenerContainerOptions
                .builder()
                // 设置线程池
                .executor(checkExecutor)
                .build();
        
        StreamMessageListenerContainer<String, MapRecord<String, String, String>> container =
                StreamMessageListenerContainer.create(connectionFactory, options);
        
        // 这里自动ack是因为即使ack后，任务出问题了，也会自动重新放回队列
        Subscription subscription = container.receive(StreamOffset
                .fromStart(TaskConstants.PIC_CHECK_STREAM_KEY), checkTaskStreamListener);
        
        container.start();
        return subscription;
    }
    
    
    /**
     * 消息的处理器
     */
    @Bean
    CheckTaskStreamListener checkTaskStreamListener() {
        return new CheckTaskStreamListener();
    }
    
}
