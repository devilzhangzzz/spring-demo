package com.zzz.seata.controller;

import com.zzz.seata.dto.BusinessDTO;
import com.zzz.seata.provider.BusinessProvider;
import com.zzz.seata.response.ObjectResponse;
import com.zzz.seata.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessController implements BusinessProvider {

    @Autowired
    private BusinessService businessService;

    @Override
    public ObjectResponse handleBusiness(BusinessDTO businessDTO) {
        return businessService.handleBusiness(businessDTO);
    }
}
