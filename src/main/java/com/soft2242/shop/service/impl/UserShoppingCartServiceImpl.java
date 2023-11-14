package com.soft2242.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft2242.shop.common.exception.ServerException;
import com.soft2242.shop.entity.Goods;
import com.soft2242.shop.entity.UserShoppingCart;
import com.soft2242.shop.mapper.GoodsMapper;
import com.soft2242.shop.mapper.UserShoppingCartMapper;
import com.soft2242.shop.query.CartQuery;
import com.soft2242.shop.service.UserShoppingCartService;
import com.soft2242.shop.vo.CartGoodsVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wg-233
 * @since 2023-11-07
 */
@Service
@AllArgsConstructor
public class UserShoppingCartServiceImpl extends ServiceImpl<UserShoppingCartMapper, UserShoppingCart> implements UserShoppingCartService {

    private final GoodsMapper goodsMapper;
    @Override
    public CartGoodsVO addShopCart(CartQuery query) {
        Goods goods = goodsMapper.selectById(query.getId());
        if (goods == null){
            throw new ServerException("商品不存在");
        }
        if (query.getCount() > goods.getInventory()){
            throw new ServerException("库存不足");
        }
        UserShoppingCart userShoppingCart = new UserShoppingCart();
        userShoppingCart.setUserId(query.getUserId());
        userShoppingCart.setGoodsId(goods.getId());
        userShoppingCart.setPrice(goods.getPrice());
        userShoppingCart.setCount(query.getCount());
        userShoppingCart.setAttrsText(query.getAttrsText());
        userShoppingCart.setSelected(false);
        baseMapper.insert(userShoppingCart);

        CartGoodsVO cartGoodsVO = new CartGoodsVO();
        cartGoodsVO.setId(userShoppingCart.getId());
        cartGoodsVO.setName(goods.getName());
        cartGoodsVO.setAttrsText(query.getAttrsText());
        cartGoodsVO.setPrice(userShoppingCart.getPrice());
        cartGoodsVO.setNowPrice(goods.getPrice());
        cartGoodsVO.setSelected(userShoppingCart.getSelected());
        cartGoodsVO.setStock(goods.getInventory());
        cartGoodsVO.setCount(query.getCount());
        cartGoodsVO.setPicture(goods.getCover());
        cartGoodsVO.setDiscount(goods.getDiscount());
        return cartGoodsVO;
    }
}
