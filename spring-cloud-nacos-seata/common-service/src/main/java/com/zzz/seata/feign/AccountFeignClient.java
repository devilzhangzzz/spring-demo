package com.zzz.seata.feign;

import com.zzz.seata.provider.AccountProvider;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("account-service")
public interface AccountFeignClient extends AccountProvider {
}
