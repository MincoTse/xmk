package com.xmk.bsf.jdbc.sharding.rule;

import lombok.Data;

/**
 * 表规则
 * <br>
 *
 * @author 明科
 * @create 2020/5/7 10:06
 */
@Data
public class TableRule {


    /**
     * 逻辑表名
     */
    private String logicTable;

    /**
     * 实际数据节点
     * 如：
     * logicTable_${0..1}
     */
    private String actualDataNodes;

    /**
     * 分片列
     */
    private String shardingColumn;

    /**
     * 分片算法表达式
     * <p>
     * logicTable_${uid % 10}
     */
    private String algorithmExpression;

    /**
     * 分片算法类名
     */
    private String algorithmClassName;

    /**
     * db分片算法类名
     */
    private String dbAlgorithmClassName;


}
