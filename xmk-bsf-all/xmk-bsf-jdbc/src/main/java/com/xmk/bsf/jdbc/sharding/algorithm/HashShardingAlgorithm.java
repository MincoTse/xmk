package com.xmk.bsf.jdbc.sharding.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;
import java.util.Optional;

/**
 * 通过手机号 分库分表
 * 账户表 、user_mini_app
 *
 * <br>
 *
 * @author 明科
 * @create 2020/5/8 17:00
 */
@Slf4j
public class HashShardingAlgorithm implements PreciseShardingAlgorithm<String> {

    private Integer shardingCount;


    public HashShardingAlgorithm(Integer shardingCount) {
        this.shardingCount = shardingCount;
    }

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {
        int index = (shardingCount - 1) & hash(shardingValue.getValue());
        Optional<String> first = availableTargetNames.stream().filter(s -> s.contains("" + index)).findFirst();
        return first.get();
    }

    /**
     * 降低寻址碰撞率
     *
     * @param key
     * @return
     */
    int hash(String key) {
        int h;
        return (h = key.hashCode()) ^ (h >>> 16);
    }
}
