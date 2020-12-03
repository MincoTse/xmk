package com.xmk.bsf.jdbc.sharding.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.xmk.bsf.jdbc.constant.DbConst;
import com.xmk.bsf.jdbc.sharding.properties.DataSourceProperties;

import javax.sql.DataSource;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/10/29 17:09
 */
public class DruidShardingDataSourceFactory implements ShardingDataSourceService {


    private DataSourceProperties dataSourceProperties;

    public DruidShardingDataSourceFactory(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    @Override
    public DataSource createDataSource(String dbName) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        dataSource.setUrl(dataSourceProperties.getUrl().replace(DbConst.DB_NAME_VARIABLE, dbName));
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());
        //dataSource.setDriverClassName(driverClassName);
        //dataSource.setUrl(url.replace(DB_NAME_VARIABLE, dbName));
        //dataSource.setUsername(username);
        //dataSource.setPassword(password);
        dataSource.setInitialSize(2);
        dataSource.setMinIdle(2);
        dataSource.setMaxActive(50);
        dataSource.setMaxWait(60000L);

        dataSource.setTimeBetweenEvictionRunsMillis(60000L);
        dataSource.setMinEvictableIdleTimeMillis(300000L);
        dataSource.setValidationQuery("SELECT 'x'");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setPoolPreparedStatements(false);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);

        return dataSource;
    }

    @Override
    public DataSource createSalveDataSource(String dbName) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        dataSource.setUrl(dataSourceProperties.getSlave().replace(DbConst.DB_NAME_VARIABLE, dbName));
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());

        dataSource.setInitialSize(1);
        dataSource.setMinIdle(1);
        dataSource.setMaxActive(200);
        dataSource.setMaxWait(60000L);
        dataSource.setTimeBetweenEvictionRunsMillis(60000L);
        dataSource.setMinEvictableIdleTimeMillis(300000L);
        dataSource.setValidationQuery("SELECT 'x'");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setPoolPreparedStatements(false);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        return dataSource;
    }
}
