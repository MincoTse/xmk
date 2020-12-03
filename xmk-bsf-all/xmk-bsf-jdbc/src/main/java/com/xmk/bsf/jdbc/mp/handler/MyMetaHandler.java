package com.xmk.bsf.jdbc.mp.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.xmk.bsf.jdbc.constant.DbConst;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

/**
 * MP 自动填充
 * <br>
 *
 * @author 明科
 * @create 2020/5/14 11:16
 */
@Slf4j
@Component
public class MyMetaHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {

        //LocalDate date = LocalDate.now();
        Date date = new Date();
        this.strictInsertFill(metaObject, DbConst.CREATE_TIME_FILE_NAME, Date.class, date);
        this.strictInsertFill(metaObject, DbConst.UPDATE_TIME_FILE_NAME, Date.class, date);
        boolean hasDeleted = metaObject.hasGetter(DbConst.DELETED_FILE_NAME);
        if (hasDeleted) {
            this.strictInsertFill(metaObject, DbConst.DELETED_FILE_NAME, Integer.class, DbConst.DELETED_FILE_DEFAULT_VALUE);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, DbConst.UPDATE_TIME_FILE_NAME, Date.class, new Date()); // 起始版本 3.3.0(推荐使用)
    }
}
