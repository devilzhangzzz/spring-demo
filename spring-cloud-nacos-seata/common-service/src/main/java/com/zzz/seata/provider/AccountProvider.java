package com.zzz.seata.provider;


import com.zzz.seata.dto.AccountDTO;
import com.zzz.seata.response.ObjectResponse;

/**
 * @Author: heshouyou
 * @Description 账户服务接口
 * @Date Created in 2019/1/13 16:37
 */
public interface AccountProvider {

    /**
     * 从账户扣钱
     */
     ObjectResponse decreaseAccount(AccountDTO accountDTO);
}
