package com.xmk.bsf.jdbc.sharding.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * sharding公用配置
 * <br>
 *
 * @author 明科
 * @create 2020/5/11 15:22
 */
@Data
@ConfigurationProperties(prefix = "qyx.algorithm")
public class AlgorithmProperties {


    /**
     * 算法对应的分片数
     * key: 类名如：HashShardingAlgorithm
     * value: 算法实际分片数
     */
    private Map<String,Integer> shardingMap;


}
