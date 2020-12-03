package com.xmk.bsf.jdbc.sharding.rule;

import lombok.Data;

/**
 * 数据库类型
 * <br>
 *
 * @author 明科
 * @create 2020/5/7 10:07
 */
@Data
public class DbRule {

    private String dbName;

    /**
     * 分片数 选填
     */
    private Integer shardingCount;


    private Integer algorithmClassName;



    private Boolean isMasterSlave = Boolean.FALSE;


}
