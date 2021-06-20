package com.zzz.seata.provider;

import com.zzz.seata.dto.BusinessDTO;
import com.zzz.seata.response.ObjectResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface BusinessProvider {

    @PostMapping("/buy")
    ObjectResponse handleBusiness(@RequestBody BusinessDTO businessDTO);
}
