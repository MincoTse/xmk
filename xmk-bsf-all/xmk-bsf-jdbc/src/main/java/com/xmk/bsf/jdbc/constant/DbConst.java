package com.xmk.bsf.jdbc.constant;

import lombok.experimental.NonFinal;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/5/29 14:20
 */
public class DbConst {

    /**
     * db_name变量
     * 考虑多数据源情况提出来变量
     */
    public static final String DB_NAME_VARIABLE = "__db.name__";

    public static final String CREATE_TIME_FILE_NAME = "createTime";

    public static final String DELETED_FILE_NAME = "deleted";

    public static final Integer DELETED_FILE_DEFAULT_VALUE = 0;

    public static final String UPDATE_TIME_FILE_NAME = "updateTime";
}
