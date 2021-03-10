package com.atguigu.serviceedu.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyMetaObjectHandler.class);
    @Override
    public void insertFill(MetaObject metaObject) {
        LOGGER.info("start insert fill ......");
        //创建时间
        this.setFieldValByName("gmtCreate",new Date(),metaObject);
        //更新时间
        this.setFieldValByName("gmtModified",new Date(),metaObject);
        //逻辑删除
        this.setFieldValByName("isDeleted",0,metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LOGGER.info("start update fill ......");
        //更新时间
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }
}
