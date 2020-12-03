package com.xmk.bsf.jdbc.sharding.algorithm.db;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;
import java.util.Optional;

/**
 * uuid通用分库规则
 * <br>
 *
 * @author 明科
 * @create 2020/5/8 17:00
 */
@Slf4j
public class UuidDbShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    private int shardingCount;


    public UuidDbShardingAlgorithm(int shardingCount) {
        this.shardingCount = shardingCount;
    }

    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {

        String uuidString = shardingValue.getValue().toString();
        int index = algorithm(uuidString);
        Optional<String> first = availableTargetNames.stream().filter(s -> s.contains("" + index)).findFirst();
        return first.get();
    }


    /**
     * 倒数第三位取余
     *
     * @param key
     * @return
     */
    int algorithm(String key) {
        int length = key.length();
        String a = key.substring((length - 3), (length - 2));
        int index = Integer.parseInt(a) % shardingCount;
        return index;
    }



}
