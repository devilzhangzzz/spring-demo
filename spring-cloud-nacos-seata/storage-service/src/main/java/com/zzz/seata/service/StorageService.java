package com.zzz.seata.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzz.seata.dto.CommodityDTO;
import com.zzz.seata.entity.TStorage;
import com.zzz.seata.enums.RspStatusEnum;
import com.zzz.seata.mapper.TStorageMapper;
import com.zzz.seata.provider.StorageProvider;
import com.zzz.seata.response.ObjectResponse;
import org.springframework.stereotype.Service;

@Service
public class StorageService extends ServiceImpl<TStorageMapper, TStorage> implements StorageProvider {

    @Override
    public ObjectResponse decreaseStorage(CommodityDTO commodityDTO) {
        int storage = baseMapper.decreaseStorage(commodityDTO.getCommodityCode(), commodityDTO.getCount());
        ObjectResponse<Object> response = new ObjectResponse<>();
        if (storage > 0){
            response.setStatus(RspStatusEnum.SUCCESS.getCode());
            response.setMessage(RspStatusEnum.SUCCESS.getMessage());
            return response;
        }

        response.setStatus(RspStatusEnum.FAIL.getCode());
        response.setMessage(RspStatusEnum.FAIL.getMessage());
        return response;
    }
}
