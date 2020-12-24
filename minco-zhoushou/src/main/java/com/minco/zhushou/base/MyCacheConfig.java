package com.minco.zhushou.base;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.SimpleCacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/12/22 14:00
 */
@EnableCaching
@Configuration
public class MyCacheConfig extends CachingConfigurerSupport {


    @Resource
    private RedisConnectionFactory connectionFactory;

    @Override
    public CacheResolver cacheResolver() {
        // 通过Guava实现的自定义堆内存缓存管理器
        CacheManager caffeineCacheManager = new CaffeineCacheManager();
        List<CacheManager> list = new ArrayList<>();
        // 优先读取堆内存缓存
        list.add(caffeineCacheManager);
        // 堆内存缓存读取不到该key时再读取redis缓存
        list.add(redisCacheManager());
        return new CustomCacheResolver(list);
    }

    @Bean
    public CacheManager redisCacheManager() {

        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(60))
                // config = config.entryTtl();
                //key的序列化 string
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                //value的序列化  json  jacksonObjectMapper
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer(JacksonConst.getRedisObjectMapper())));

        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory))
                .cacheDefaults(config).build();
    }


    @Override
    public CacheErrorHandler errorHandler() {

        //return new IgnoreCacheErrorHandler();
        return new SimpleCacheErrorHandler();
    }


}
