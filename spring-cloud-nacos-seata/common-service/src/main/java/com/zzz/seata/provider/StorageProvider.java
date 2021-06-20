package com.zzz.seata.provider;


import com.zzz.seata.dto.CommodityDTO;
import com.zzz.seata.response.ObjectResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: heshouyou
 * @Description  仓库服务
 * @Date Created in 2019/1/13 16:22
 */
public interface StorageProvider {

    /**
     * 扣减库存
     */
    @PostMapping("decreaseStorage")
    ObjectResponse decreaseStorage(@RequestBody CommodityDTO commodityDTO);
}
