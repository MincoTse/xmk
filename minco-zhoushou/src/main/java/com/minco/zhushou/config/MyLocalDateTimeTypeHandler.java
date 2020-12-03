package com.minco.zhushou.config;

import org.apache.ibatis.type.LocalDateTimeTypeHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/11/7 22:31
 */
public class MyLocalDateTimeTypeHandler extends LocalDateTimeTypeHandler {

    @Override
    public LocalDateTime getResult(ResultSet rs, String columnName) throws SQLException {
        Object object = rs.getObject(columnName);
        System.out.println(object);
        if(object instanceof java.sql.Timestamp){//在这里强行转换，将sql的时间转换为LocalDateTime
            return LocalDateTime//可以根据自己的需要进行转化
                    .ofInstant(((Timestamp)object).toInstant(), ZoneOffset.ofHours(0));
        }
        return super.getResult(rs, columnName);
    }
}