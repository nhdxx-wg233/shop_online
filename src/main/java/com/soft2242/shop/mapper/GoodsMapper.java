package com.soft2242.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft2242.shop.entity.Goods;
import com.soft2242.shop.vo.UserOrderGoodsVO;
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
public interface GoodsMapper extends BaseMapper<Goods> {
    List<UserOrderGoodsVO> getGoodsListByOrderId(@Param("id") Integer id);
}
