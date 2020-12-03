package com.xmk.bsf.jdbc.sharding.datasource;

import com.xmk.bsf.jdbc.constant.DbConst;
import com.xmk.bsf.jdbc.sharding.properties.DataSourceProperties;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/10/29 17:01
 */
public class HikariShardingDataSourceFactory implements ShardingDataSourceService {

    private DataSourceProperties dataSourceProperties;

    public HikariShardingDataSourceFactory(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    @Override
    public DataSource createDataSource(String dbName) {
        HikariDataSource ds = new HikariDataSource();
        ds.setDriverClassName(dataSourceProperties.getDriverClassName());
        ds.setJdbcUrl(dataSourceProperties.getUrl().replace(DbConst.DB_NAME_VARIABLE, dbName));
        ds.setUsername(dataSourceProperties.getUsername());
        ds.setPassword(dataSourceProperties.getPassword());

        //等待连接池分配连接的最大时长
        ds.setConnectionTimeout(TimeUnit.SECONDS.toMillis(30));
        //连接空闲的时长
        ds.setIdleTimeout(TimeUnit.MINUTES.toMillis(10));
        //连接最长存活时间
        ds.setMaxLifetime(TimeUnit.MINUTES.toMillis(30));

        ds.setMinimumIdle(5);
        ds.setMaximumPoolSize(60);
        return ds;
    }

    @Override
    public DataSource createSalveDataSource(String dbName) {
        HikariDataSource ds = new HikariDataSource();
        ds.setDriverClassName(dataSourceProperties.getDriverClassName());
        ds.setJdbcUrl(dataSourceProperties.getSlave().replace(DbConst.DB_NAME_VARIABLE, dbName));
        ds.setUsername(dataSourceProperties.getUsername());
        ds.setPassword(dataSourceProperties.getPassword());

        //等待连接池分配连接的最大时长
        ds.setConnectionTimeout(TimeUnit.SECONDS.toMillis(30));
        //连接空闲的时长
        ds.setIdleTimeout(TimeUnit.MINUTES.toMillis(10));
        //连接最长存活时间
        ds.setMaxLifetime(TimeUnit.MINUTES.toMillis(30));

        ds.setMinimumIdle(5);
        ds.setMaximumPoolSize(60);
        return ds;
    }
}
