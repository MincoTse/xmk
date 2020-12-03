package com.xmk.bsf.jdbc.mp;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Random;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/4/24 10:09
 */
@Configuration
@MapperScan({"com.minco.zhushou.dao"})
public class MybatisPlusConfig {


    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {

        PaginationInnerInterceptor pageInnerInterceptor = new PaginationInnerInterceptor();
        //避免每次分页都去抓取数据库类型
        pageInnerInterceptor.setDbType(DbType.MYSQL);
        pageInnerInterceptor.setMaxLimit(100L);

        DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor = new DynamicTableNameInnerInterceptor();
        HashMap<String, TableNameHandler> map = new HashMap<String, TableNameHandler>(2) {{
            put("user", (sql, tableName) -> {
                String year = "_2018";
                int random = new Random().nextInt(10);
                if (random % 2 == 1) {
                    year = "_2019";
                }
                return tableName + year;
            });
        }};
        dynamicTableNameInnerInterceptor.setTableNameHandlerMap(map);


        BlockAttackInnerInterceptor blockAttackInnerInterceptor = new BlockAttackInnerInterceptor();


        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(pageInnerInterceptor);
        interceptor.addInnerInterceptor(blockAttackInnerInterceptor);
        //动态表名 类似分库分表
        //interceptor.addInnerInterceptor(dynamicTableNameInnerInterceptor);

        return interceptor;
    }


    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setUseDeprecatedExecutor(false);
    }

}
