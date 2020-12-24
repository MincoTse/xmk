package com.minco.zhushou.base;

import com.xmk.common.exception.Assert;
import com.xmk.common.exception.CommonCodeMessage;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/12/22 14:19
 */
public class CustomCacheResolver implements CacheResolver, InitializingBean {
    @Nullable
    private List<CacheManager> cacheManagerList;

    public CustomCacheResolver(){
    }
    public CustomCacheResolver(List<CacheManager> cacheManagerList){
        this.cacheManagerList = cacheManagerList;
    }

    public List<CacheManager> getCacheManagerList() {
        return cacheManagerList;
    }

    @Override
    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
        Collection<String> cacheNames = getCacheNames(context);
        if (cacheNames == null) {
            return Collections.emptyList();
        }
        Collection<Cache> result = new ArrayList<>();
        for(CacheManager cacheManager : getCacheManagerList()){
            for (String cacheName : cacheNames) {
                Cache cache = cacheManager.getCache(cacheName);
                if (cache == null) {
                    throw new IllegalArgumentException("Cannot find cache named '" +
                            cacheName + "' for " + context.getOperation());
                }
                result.add(cache);
            }
        }
        return result;
    }

    private Collection<String> getCacheNames(CacheOperationInvocationContext<?> context){
        return context.getOperation().getCacheNames();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.cacheManagerList, CommonCodeMessage.BUSINESS_CUSTOM_EXCEPTION,"自定义code为空");

    }
}
