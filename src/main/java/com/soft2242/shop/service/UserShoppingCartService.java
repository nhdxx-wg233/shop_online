package com.soft2242.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soft2242.shop.entity.UserShoppingCart;
import com.soft2242.shop.query.CartQuery;
import com.soft2242.shop.query.EditCartQuery;
import com.soft2242.shop.vo.CartGoodsVO;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wg-233
 * @since 2023-11-07
 */
public interface UserShoppingCartService extends IService<UserShoppingCart> {
    CartGoodsVO addShopCart(CartQuery query);

    List<CartGoodsVO> shopCartList(Integer userId);

    CartGoodsVO editCart(EditCartQuery query);

    void removeCartGoods(Integer userId, List<Integer> ids);
    void editCartSelected(Boolean selected, Integer userId);
}
