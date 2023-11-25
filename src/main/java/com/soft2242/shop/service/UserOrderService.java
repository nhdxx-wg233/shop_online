package com.soft2242.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soft2242.shop.entity.UserOrder;
import com.soft2242.shop.query.OrderPreQuery;
import com.soft2242.shop.vo.OrderDetailVO;
import com.soft2242.shop.vo.SubmitOrderVO;
import com.soft2242.shop.vo.UserOrderVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wg-233
 * @since 2023-11-07
 */
public interface UserOrderService extends IService<UserOrder> {
    /**
     * 提交订单
     */
    Integer addGoodsOrder(UserOrderVO orderVO);

    OrderDetailVO getOrderDetail(Integer id);

    SubmitOrderVO getPreOrderDetail(Integer userId);

    SubmitOrderVO getPreNowOrderDetail(OrderPreQuery query);

    SubmitOrderVO getRepurchaseOrderDetail(Integer id);
}
