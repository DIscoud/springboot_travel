package com.lemon.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.sun.prism.impl.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @ClassName MyMetaObjectHandler
 * @Description 自定义元数据对象处理器(自动填充字段)
 * @Author lemon
 * @Date 14:04 2022/01/29
 * @Version 2.1
 **/
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 新增操作自动填充
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段填充insert：");
        log.info(metaObject.toString());
        metaObject.setValue("rutTime", LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }


}
