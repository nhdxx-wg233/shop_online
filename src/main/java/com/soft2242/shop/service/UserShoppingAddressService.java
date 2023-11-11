package com.soft2242.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soft2242.shop.entity.UserShoppingAddress;
import com.soft2242.shop.vo.AddressVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wg-233
 * @since 2023-11-07
 */
public interface UserShoppingAddressService extends IService<UserShoppingAddress> {
    Integer saveShoppingAddress(AddressVO addressVO);
    Integer editShoppingAddress(AddressVO addressVO);

}
