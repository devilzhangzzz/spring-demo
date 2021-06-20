package com.zzz.seata.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzz.seata.entity.TStorage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author heshouyou
 * @since 2019-01-13
 */
@Mapper
public interface TStorageMapper extends BaseMapper<TStorage> {

    /**
     * 扣减商品库存
     * @Param: commodityCode 商品code  count扣减数量
     * @Return:
     */
    int decreaseStorage(@Param("commodityCode") String commodityCode, @Param("count") Integer count);
}
