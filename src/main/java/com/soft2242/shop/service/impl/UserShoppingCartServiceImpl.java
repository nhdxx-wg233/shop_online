package com.soft2242.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft2242.shop.entity.UserShoppingCart;
import com.soft2242.shop.mapper.UserShoppingCartMapper;
import com.soft2242.shop.service.UserShoppingCartService;
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
public class UserShoppingCartServiceImpl extends ServiceImpl<UserShoppingCartMapper, UserShoppingCart> implements UserShoppingCartService {

}