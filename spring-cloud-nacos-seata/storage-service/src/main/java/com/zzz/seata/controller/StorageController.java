package com.zzz.seata.controller;

import com.zzz.seata.dto.CommodityDTO;
import com.zzz.seata.provider.StorageProvider;
import com.zzz.seata.response.ObjectResponse;
import com.zzz.seata.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController implements StorageProvider {

    @Autowired
    private StorageService storageService;

    @Override
    public ObjectResponse decreaseStorage(@RequestBody CommodityDTO commodityDTO) {
        return storageService.decreaseStorage(commodityDTO);
    }
}
