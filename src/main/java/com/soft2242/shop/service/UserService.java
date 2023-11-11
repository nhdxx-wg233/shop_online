package com.soft2242.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soft2242.shop.entity.User;
import com.soft2242.shop.query.UserLoginQuery;
import com.soft2242.shop.vo.LoginResultVO;
import com.soft2242.shop.vo.UserVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wg-233
 * @since 2023-11-07
 */
public interface UserService extends IService<User> {
    /**
     * 登录方法
     *
     * @param query 登录查询对象
     * @return 登录结果对象
     */
    LoginResultVO login(UserLoginQuery query);
    /**
     * 根据用户ID获取用户信息
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    User getUserInfo(Integer userId);

    /**
     * 编辑用户信息
     *
     * @param userVO 用户信息实体对象
     * @return 编辑后的用户信息实体对象
     */
    UserVO editUserInfo(UserVO userVO);

}
