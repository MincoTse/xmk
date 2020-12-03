package com.xmk.bsf.jdbc.sharding;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xmk.bsf.jdbc.constant.CommonConst;
import com.xmk.bsf.jdbc.constant.NumberConst;
import com.xmk.bsf.jdbc.sharding.datasource.DruidShardingDataSourceFactory;
import com.xmk.bsf.jdbc.sharding.datasource.HikariShardingDataSourceFactory;
import com.xmk.bsf.jdbc.sharding.datasource.ShardingDataSourceService;
import com.xmk.bsf.jdbc.sharding.properties.AlgorithmProperties;
import com.xmk.bsf.jdbc.sharding.properties.DataSourceProperties;
import com.xmk.bsf.jdbc.sharding.rule.DbRule;
import com.xmk.bsf.jdbc.sharding.rule.TableRule;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.config.masterslave.MasterSlaveRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.ShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/5/7 14:17
 */
@Slf4j
@Configuration
@Data
@ConditionalOnProperty(value = "qyx.server.type", havingValue = "service")
@EnableConfigurationProperties({
        DataSourceProperties.class,
        AlgorithmProperties.class,
})
public class DataSourceConfig {

    private static final int DEFAULT_SHARDING_COUNT = 1;
    /**
     * table分片算法basepackage
     */
    private static final String BASE_ALGORITHM_PACKAGE = "com.quyixian.core.sharding.algorithm.db.%s";

    private static final String DB_UUID_ALGORITHM_CLASS_NAME = "UuidDbShardingAlgorithm";


    private static final String ACTUAL_NODE = "${0..%s}";

    private static List<String> non_uuid_table = Lists.newArrayList("t_user_qq", "t_user_we_chat", "t_user_apple");

    /**
     * 主库别名的后缀
     */
    private static final String MASTER_SUFFIX = "_master";
    /**
     * 从库别名的后缀
     */
    private static final String SLAVE_SUFFIX = "_slave";


    private DataSourceProperties dataSourceProperties;

    private AlgorithmProperties algorithmProperties;


    private ShardingDataSourceService shardingDataSourceFactory;


    private Environment environment;

    @Autowired
    public DataSourceConfig(Environment environment, DataSourceProperties dataSourceProperties, AlgorithmProperties algorithmProperties) {
        this.environment = environment;
        this.dataSourceProperties = dataSourceProperties;
        this.algorithmProperties = algorithmProperties;
        this.shardingDataSourceFactory = new HikariShardingDataSourceFactory(dataSourceProperties);
    }

    private Properties getProperties() {
        Boolean showSql = Optional.ofNullable(dataSourceProperties.getSqlShow()).orElseGet(() -> Boolean.TRUE);
        Boolean showSimple = Optional.ofNullable(dataSourceProperties.getSqlSimple()).orElseGet(() -> Boolean.FALSE);
        //if (!showValue) {
        //    showSimple = Boolean.FALSE;
        //}

        Properties properties = new Properties();
        properties.setProperty("sql.show", showSql.toString());
        properties.setProperty("sql.simple", showSimple.toString());
        return properties;
    }

    @Bean
    @Primary
    public DataSource dataSource() throws SQLException {
        Map<String, DataSource> dataSourceMap = Maps.newHashMap();

        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        List<MasterSlaveRuleConfiguration> masterSlaveRuleConfigurations = Lists.newArrayList();
        List<DbRule> dbs = Binder.get(environment).bind("db.name", Bindable.listOf(DbRule.class)).orElse(emptyList());

        dbs.forEach(dbRule -> {
            String dbName = dbRule.getDbName();
            int shardingCount = Optional.ofNullable(dbRule.getShardingCount()).orElseGet(() -> DEFAULT_SHARDING_COUNT);
            //分片数大于1 则代表DB是分库
            Boolean isShardingDb = shardingCount > 1 ? Boolean.TRUE : Boolean.FALSE;

            for (int i = 0; i < shardingCount; i++) {
                StringBuilder stringBuilder = new StringBuilder(dbRule.getDbName());
                if (isShardingDb) {
                    stringBuilder.append("_").append(i);
                }
                //真实数据库名 如:不分库 db; 分库db_0
                String realDbName = stringBuilder.toString();
                //配置Db层面信息
                configDbInfo(dataSourceMap, masterSlaveRuleConfigurations, dbRule, realDbName);
            }

            List<TableRule> tableRules = Binder.get(environment).bind(tableRulePrefix(dbName),
                    Bindable.listOf(TableRule.class)).orElse(emptyList());
            List<TableRuleConfiguration> collect = tableRules.stream()
                    .map(tableRule -> {

                        //获取actualDataNode  需要兼容分库的或者没有分库的    分表  或者 没有分表
                        String actualDataNode = getActualDataNode(dbRule, tableRule);
                        TableRuleConfiguration tableRuleConfiguration = new TableRuleConfiguration(tableRule.getLogicTable(), actualDataNode);

                        //设置数据库分片规则，没配置就无
                        tableRuleConfiguration.setDatabaseShardingStrategyConfig(getDatabaseShardingStrategyConfig(dbRule, tableRule));

                        //设置表分片规则，没配置就无
                        tableRuleConfiguration.setTableShardingStrategyConfig(getTableShardingStrategyConfig(tableRule));
                        return tableRuleConfiguration;
                    })
                    .collect(Collectors.toList());
            shardingRuleConfig.getTableRuleConfigs().addAll(collect);
        });


        shardingRuleConfig.setMasterSlaveRuleConfigs(masterSlaveRuleConfigurations);

        setOtherShardingRule(shardingRuleConfig);

        return ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, getProperties());
    }


    private void configDbInfo(Map<String, DataSource> dataSourceMap, List<MasterSlaveRuleConfiguration> masterSlaveRuleConfigurations, DbRule dbRule, String realDbName) {
        if (!dbRule.getIsMasterSlave()) {
            createSingleDataSource(dataSourceMap, realDbName);
            return;
        }

        //设置主从数据源
        createMasterSalveDataSourceMap(dataSourceMap, realDbName);

        //配置主从规则
        masterSlaveRuleConfigurations.add(getMasterSlaveRuleConfiguration(realDbName));
    }


    /**
     * 创建单个Db数据源
     *
     * @param dataSourceMap
     * @param realDbName
     */
    private void createSingleDataSource(Map<String, DataSource> dataSourceMap, String realDbName) {

        dataSourceMap.put(realDbName, shardingDataSourceFactory.createDataSource(realDbName));
    }


    /**
     * 创建并设置主从数据源
     *
     * @param dataSourceMap
     * @param realDbName
     */
    private void createMasterSalveDataSourceMap(Map<String, DataSource> dataSourceMap, String realDbName) {

        String slaveName = new StringBuilder(realDbName)
                .append(MASTER_SUFFIX)
                .toString();
        //设置主数据源
        dataSourceMap.put(slaveName, shardingDataSourceFactory.createDataSource(realDbName));

        String masterName = new StringBuilder(realDbName)
                .append(SLAVE_SUFFIX)
                .toString();
        //设置从数据源
        dataSourceMap.put(masterName, shardingDataSourceFactory.createSalveDataSource(realDbName));
    }


    private MasterSlaveRuleConfiguration getMasterSlaveRuleConfiguration(String realDbName) {
        String slaveName = new StringBuilder(realDbName)
                .append(SLAVE_SUFFIX)
                .toString();

        String masterName = new StringBuilder(realDbName)
                .append(MASTER_SUFFIX)
                .toString();

        return new MasterSlaveRuleConfiguration(realDbName, masterName, Arrays.asList(slaveName));
    }


    /**
     * 获取表分片策略
     *
     * @param tableRule
     * @return
     */
    private ShardingStrategyConfiguration getTableShardingStrategyConfig(TableRule tableRule) {

        ShardingStrategyConfiguration shardingStrategyConfiguration = null;

        if (StrUtil.isNotBlank(tableRule.getAlgorithmExpression())) {
            String algorithmExpression = new StringBuilder(tableRule.getLogicTable())
                    .append(CommonConst.UNDERLINE)
                    .append(tableRule.getAlgorithmExpression())
                    .toString();
            shardingStrategyConfiguration = getInlineShardingStrategyConfiguration(tableRule.getShardingColumn(), algorithmExpression);
        } else if (StrUtil.isNotBlank(tableRule.getAlgorithmClassName())) {
            shardingStrategyConfiguration = getStandardShardingStrategyConfiguration(tableRule);
        } else {
            //无分片规则
        }

        return shardingStrategyConfiguration;
    }

    /**
     * 设置数据分片策略
     *
     * @param dbRule
     * @param tableRule
     * @return
     */
    private ShardingStrategyConfiguration getDatabaseShardingStrategyConfig(DbRule dbRule, TableRule tableRule) {
        ShardingStrategyConfiguration shardingStrategyConfiguration = null;

        if (StrUtil.isNotBlank(tableRule.getShardingColumn())) {
            String shardingColumn = "uuid";
            String classPackage = String.format(BASE_ALGORITHM_PACKAGE, DB_UUID_ALGORITHM_CLASS_NAME);
            String className = DB_UUID_ALGORITHM_CLASS_NAME;
            try {
                Class<PreciseShardingAlgorithm> algorithmClass = (Class<PreciseShardingAlgorithm>) Class.forName(classPackage);
                Constructor<PreciseShardingAlgorithm> constructor = (Constructor<PreciseShardingAlgorithm>) algorithmClass.getConstructors()[0];
                PreciseShardingAlgorithm shardingAlgorithm;
                if (constructor.getParameterCount() > 0) {
                    Map<String, Integer> shardingMap = algorithmProperties.getShardingMap();
                    Integer shardingCount = Optional.ofNullable(shardingMap.get(className)).orElse(NumberConst.TWO);
                    shardingAlgorithm = constructor.newInstance(shardingCount);
                } else {
                    shardingAlgorithm = constructor.newInstance();
                }

                shardingStrategyConfiguration = new StandardShardingStrategyConfiguration(shardingColumn, shardingAlgorithm);
            } catch (Exception e) {
                log.error("init sharding db rule err.", e);
            }
        } else {

        }

        return shardingStrategyConfiguration;
    }




    private ShardingStrategyConfiguration getInlineShardingStrategyConfiguration(String shardingColumn, String algorithmExpression) {
        return new InlineShardingStrategyConfiguration(shardingColumn, algorithmExpression);
    }

    private ShardingStrategyConfiguration getStandardShardingStrategyConfiguration(TableRule tableRule) {
        String className = String.format(BASE_ALGORITHM_PACKAGE, tableRule.getAlgorithmClassName());
        StandardShardingStrategyConfiguration standardShardingStrategyConfiguration = null;
        try {
            Class<PreciseShardingAlgorithm> algorithmClass = (Class<PreciseShardingAlgorithm>) Class.forName(className);
            Constructor<PreciseShardingAlgorithm> constructor = (Constructor<PreciseShardingAlgorithm>) algorithmClass.getConstructors()[0];
            PreciseShardingAlgorithm shardingAlgorithm;
            if (constructor.getParameterCount() > 0) {
                Map<String, Integer> shardingMap = algorithmProperties.getShardingMap();
                Integer shardingCount = Optional.ofNullable(shardingMap.get(tableRule.getAlgorithmClassName())).orElse(NumberConst.TWO);
                shardingAlgorithm = constructor.newInstance(shardingCount);
            } else {
                shardingAlgorithm = constructor.newInstance();
            }
            standardShardingStrategyConfiguration = new StandardShardingStrategyConfiguration(tableRule.getShardingColumn(), shardingAlgorithm);
        } catch (Exception e) {
            log.error("init sharding rule err.", e);
        }
        return standardShardingStrategyConfiguration;
    }


    private String tableRulePrefix(String dbName) {
        StringBuilder stringBuilder = new StringBuilder("db.rules.")
                .append(dbName)
                .append(".table-rules");
        String rulePath = stringBuilder.toString().replaceAll("_", "-");
        return rulePath;
    }


    private String getActualDataNode(DbRule dbRule, TableRule tableRule) {

        String actualDb = getActualDbNode(dbRule);
        StringBuilder actualDataNodes = new StringBuilder(actualDb)
                .append(".")
                .append(tableRule.getActualDataNodes());
        return actualDataNodes.toString();
    }

    private String getActualDbNode(DbRule dbRule) {
        String actualDb = dbRule.getDbName();
        Integer shardingCount = Optional.ofNullable(dbRule.getShardingCount()).orElseGet(() -> DEFAULT_SHARDING_COUNT);
        if (shardingCount > 1) {
            Integer lastIndex = shardingCount - 1;
            String actualNode = String.format(ACTUAL_NODE, lastIndex);
            actualDb = new StringBuilder(actualDb).append(CommonConst.UNDERLINE)
                    .append(actualNode).toString();
        }
        return actualDb;
    }


    private void setOtherShardingRule(ShardingRuleConfiguration shardingRuleConfig) {
        //设置广播表
        setBroadcastTables(shardingRuleConfig);
        //绑定关联表
        setBindTables(shardingRuleConfig);
    }

    private void setBroadcastTables(ShardingRuleConfiguration shardingRuleConfig) {
        List<String> broadcastTables = dataSourceProperties.getBroadcastTables();
        if (CollUtil.isNotEmpty(broadcastTables)) {
            shardingRuleConfig.getBroadcastTables().addAll(broadcastTables);
        }
    }

    private void setBindTables(ShardingRuleConfiguration shardingRuleConfig) {
        List<String> bindTables = dataSourceProperties.getBindTables();
        if (CollUtil.isNotEmpty(bindTables)) {
            shardingRuleConfig.getBindingTableGroups().addAll(bindTables);
        }
    }


}
