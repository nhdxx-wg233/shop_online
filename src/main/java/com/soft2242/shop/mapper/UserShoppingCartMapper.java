package com.soft2242.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft2242.shop.entity.UserShoppingCart;
import com.soft2242.shop.vo.CartGoodsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wg-233
 * @since 2023-11-07
 */
public interface UserShoppingCartMapper extends BaseMapper<UserShoppingCart> {
    List<CartGoodsVO> getCartGoodsInfo(@Param("id") Integer id);

}
