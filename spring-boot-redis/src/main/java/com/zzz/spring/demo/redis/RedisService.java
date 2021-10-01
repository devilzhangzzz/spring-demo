package com.zzz.spring.demo.redis;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.google.common.base.Preconditions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
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

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * redisService
 *
 * @author zhangzhizhong
 */
@Component
@RestController("redis")
@Api("REDIS")
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

    @PostMapping("testCache3")
    @Cached(area = "longTime", name = "testCache3::", cacheType = CacheType.REMOTE)
    @CacheRefresh(refresh = 2, stopRefreshAfterLastAccess = 48, timeUnit = TimeUnit.HOURS)
    public String testCache3(String s) {
        return s + "sss";
    }

    @PostMapping("removeTestCache")
    public String    removeTestCache() {
        this.removeTestCache3();
        return "removed";
    }

    @CacheInvalidate(area = "longTime", name = "testCache3::")
    private void removeTestCache3() {

    }

    @PostMapping("testSwagger")
    @ApiOperation(value = "testSwagger", notes = "testSwagger")
    public Result<SwaggerDemoDTO> testSwagger() {
        return Result.success(new SwaggerDemoDTO());
    }

    @PostMapping("testSwagger2")
    @ApiOperation(value = "testSwagger2", notes = "testSwagger2")
    public ServiceResult<SwaggerDemoDTO> testSwagger2() {
        return ServiceResult.ofSuccess(new SwaggerDemoDTO());
    }



    @Data
    @ApiModel
    public static class SwaggerDemoDTO {

        @ApiModelProperty("结果")
        private List<String> rows;

        @ApiModelProperty("年龄")
        private String age;
    }




}
