package com.xmk.bsf.jdbc.sharding.algorithm;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;
import java.util.Optional;

/**
 * db存在分库的时候 指定默认库（及不分库）
 * <br>
 *
 * @author 明科
 * @create 2020/4/27 13:10
 */
@Slf4j
public class AppointDbShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        log.debug("UserIdShardingAlgorithm collection:" + JSON.toJSONString(collection) + ",preciseShardingValue:" + JSON.toJSONString(preciseShardingValue));
        Optional<String> first = collection.stream().findFirst();
        return first.get();
    }

}
