package com.soft2242.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft2242.shop.entity.UserOrderGoods;
import com.soft2242.shop.mapper.UserOrderGoodsMapper;
import com.soft2242.shop.service.UserOrderGoodsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wg-233
 * @since 2023-11-07
 */
@Service
public class UserOrderGoodsServiceImpl extends ServiceImpl<UserOrderGoodsMapper, UserOrderGoods> implements UserOrderGoodsService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchUserOrderGoods(List<UserOrderGoods> orderGoodsList) {
        saveBatch(orderGoodsList);
    }
}
