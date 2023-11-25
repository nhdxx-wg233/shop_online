package com.soft2242.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soft2242.shop.entity.UserOrderGoods;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wg-233
 * @since 2023-11-07
 */
public interface UserOrderGoodsService extends IService<UserOrderGoods> {

    void batchUserOrderGoods(List<UserOrderGoods> orderGoodsList);
}
