package com.xmk.bsf.jdbc.sharding.datasource;

import javax.sql.DataSource;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/10/29 16:57
 */
public interface ShardingDataSourceService {

    DataSource createDataSource(String dbName);


    DataSource createSalveDataSource(String dbName);
}
