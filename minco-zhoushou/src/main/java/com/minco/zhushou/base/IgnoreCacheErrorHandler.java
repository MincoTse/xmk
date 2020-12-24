package com.minco.zhushou.base;

import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/12/22 13:48
 */
public class IgnoreCacheErrorHandler implements CacheErrorHandler {

    @Override
    public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
        System.out.println("handleCacheGetError --------------------------");
    }

    @Override
    public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
        System.out.println("handleCachePutError --------------------------");
    }

    @Override
    public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
        System.out.println("handleCacheEvictError --------------------------");
    }

    @Override
    public void handleCacheClearError(RuntimeException exception, Cache cache) {
        System.out.println("handleCacheClearError --------------------------");
    }
}
