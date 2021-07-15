package com.zzz.spring.demo.redis;

import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * redisService
 *
 * @author zhangzhizhong
 */
@Component
@RestController("redis")
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public <T> void addByBloomFilter(BloomFilterHelper<T> bloomFilterHelper, String key, T value) {
        Preconditions.checkArgument(bloomFilterHelper != null, "bloomFilterHelper不能为空");
        int[] offset = bloomFilterHelper.murmurHashOffset(value);
        for (int i : offset) {
            redisTemplate.opsForValue().setBit(key, i, true);
        }
    }

    /**
     * 根据给定的布隆过滤器判断值是否存在
     */
    public <T> boolean includeByBloomFilter(BloomFilterHelper<T> bloomFilterHelper, String key, T value) {
        Preconditions.checkArgument(bloomFilterHelper != null, "bloomFilterHelper不能为空");
        int[] offset = bloomFilterHelper.murmurHashOffset(value);
        for (int i : offset) {
            if (!redisTemplate.opsForValue().getBit(key, i)) {
                return false;
            }
        }
        return true;
    }

    @Autowired
    private ApplicationContext context;

    @GetMapping("testCache")
    @Cacheable(value = "testCache", key = "#id")
    public String testCache(String id) {

        CacheManager bean = context.getBean(CacheManager.class);
        System.out.println(bean);
        return id + "_zzz";
    }

    @PostMapping("testCache2")
    @Cached(area = "longTime", name = "SysResourceService::isHavePermission", cacheType = CacheType.REMOTE)
    @CacheRefresh(refresh = 2, stopRefreshAfterLastAccess = 48, timeUnit = TimeUnit.HOURS)
    public String testCache2(@RequestBody IsHavePermissionVO vo) {
        return vo.getUrl();
    }

}
