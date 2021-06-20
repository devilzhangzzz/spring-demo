package com.zzz.seata.provider;

import com.zzz.seata.dto.OrderDTO;
import com.zzz.seata.response.ObjectResponse;

/**
 * @Author: heshouyou
 * @Description  订单服务接口
 * @Date Created in 2019/1/13 16:28
 */
public interface OrderProvider {

    /**
     * 创建订单
     */
    ObjectResponse<OrderDTO> createOrder(OrderDTO orderDTO);
}
