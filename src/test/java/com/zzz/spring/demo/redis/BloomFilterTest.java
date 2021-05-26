package com.zzz.spring.demo.redis;

import com.google.common.base.Charsets;
import com.google.common.hash.Funnel;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TODO
 *
 * @author zhangzhizhong
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BloomFilterTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void test() {
        BloomFilterHelper<String> myBloomFilterHelper = new BloomFilterHelper<>((Funnel<String>) (from,  into) -> into.putString(from, Charsets.UTF_8).putString(from, Charsets.UTF_8),
                1500000, 
                0.00001);
        String key = "usercode_exists_bloom";
        for (int i = 0; i < 10000; i++) {
            redisService.addByBloomFilter(myBloomFilterHelper, key, "user" + i);
        }
        System.out.println(redisService.includeByBloomFilter(myBloomFilterHelper, key, "user566"));
        System.out.println(redisService.includeByBloomFilter(myBloomFilterHelper, key, "user233668"));

    }
}
