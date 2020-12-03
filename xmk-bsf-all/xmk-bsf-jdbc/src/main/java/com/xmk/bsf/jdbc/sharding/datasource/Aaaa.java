package com.xmk.bsf.jdbc.sharding.datasource;

import org.apache.shardingsphere.api.config.masterslave.MasterSlaveRuleConfiguration;

import java.util.Arrays;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/10/29 17:14
 */
public class Aaaa {

    /**
     * 主库别名的后缀
     */
    private static final String MASTER_SUFFIX = "_master";
    /**
     * 从库别名的后缀
     */
    private static final String SLAVE_SUFFIX = "_slave";

    private MasterSlaveRuleConfiguration getMasterSlaveRuleConfiguration(String realDbName) {
        String slaveName = new StringBuilder(realDbName)
                .append(SLAVE_SUFFIX)
                .toString();

        String masterName = new StringBuilder(realDbName)
                .append(MASTER_SUFFIX)
                .toString();

        return new MasterSlaveRuleConfiguration(realDbName, masterName, Arrays.asList(slaveName));
    }

}
