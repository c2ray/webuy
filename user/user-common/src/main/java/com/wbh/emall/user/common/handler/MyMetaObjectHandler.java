package com.wbh.emall.user.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

/**
 * @author WBH
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    
    
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "gmtCreate", Date.class, Date.from(Instant.now()));
    }
    
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "gmtUpdate", Date.class, Date.from(Instant.now()));
    }
}