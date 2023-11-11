package com.soft2242.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soft2242.shop.entity.User;
import com.soft2242.shop.query.UserLoginQuery;
import com.soft2242.shop.vo.LoginResultVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wg-233
 * @since 2023-11-07
 */
public interface UserService extends IService<User> {
    LoginResultVO login(UserLoginQuery query);

}
