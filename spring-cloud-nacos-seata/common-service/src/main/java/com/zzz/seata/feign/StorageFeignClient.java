package com.zzz.seata.feign;

import com.zzz.seata.provider.AccountProvider;
import com.zzz.seata.provider.StorageProvider;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("storage-service")
public interface StorageFeignClient extends StorageProvider {
}
