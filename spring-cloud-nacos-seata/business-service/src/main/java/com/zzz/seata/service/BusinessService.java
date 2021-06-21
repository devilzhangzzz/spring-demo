package com.zzz.seata.service;

import com.zzz.seata.dto.BusinessDTO;
import com.zzz.seata.dto.CommodityDTO;
import com.zzz.seata.enums.RspStatusEnum;
import com.zzz.seata.feign.StorageFeignClient;
import com.zzz.seata.provider.BusinessProvider;
import com.zzz.seata.response.ObjectResponse;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessService implements BusinessProvider {



    @Autowired
    private StorageFeignClient storageFeignClient;


    @Override
    @GlobalTransactional(timeoutMills = 300000, name = "dubbo-gts-seata-example")
    public ObjectResponse handleBusiness(BusinessDTO businessDTO) {
        System.out.println("开始全局事务，XID = " + RootContext.getXID());
        ObjectResponse<Object> objectResponse = new ObjectResponse<>();
        //1、扣减库存
        CommodityDTO commodityDTO = new CommodityDTO();
        commodityDTO.setCommodityCode(businessDTO.getCommodityCode());
        commodityDTO.setCount(businessDTO.getCount());
        ObjectResponse storageResponse = storageFeignClient.decreaseStorage(commodityDTO);
        //2、创建订单
//        OrderDTO orderDTO = new OrderDTO();
//        orderDTO.setUserId(businessDTO.getUserId());
//        orderDTO.setCommodityCode(businessDTO.getCommodityCode());
//        orderDTO.setOrderCount(businessDTO.getCount());
//        orderDTO.setOrderAmount(businessDTO.getAmount());
//        ObjectResponse<OrderDTO> response = orderDubboService.createOrder(orderDTO);

        //打开注释测试事务发生异常后，全局回滚功能
//        if (!flag) {
//            throw new RuntimeException("测试抛异常后，分布式事务回滚！");
//        }

//        if (storageResponse.getStatus() != 200 || response.getStatus() != 200) {
//            throw new DefaultException(RspStatusEnum.FAIL);
//        }

        objectResponse.setStatus(RspStatusEnum.SUCCESS.getCode());
        objectResponse.setMessage(RspStatusEnum.SUCCESS.getMessage());
//        objectResponse.setData(response.getData());
        return storageResponse;
    }
}
