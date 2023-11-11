package com.soft2242.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soft2242.shop.entity.Goods;
import com.soft2242.shop.query.RecommendByTabGoodsQuery;
import com.soft2242.shop.vo.IndexTabRecommendVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wg-233
 * @since 2023-11-07
 */
public interface GoodsService extends IService<Goods> {
    IndexTabRecommendVO getTabRecommendGoodsByTabId(RecommendByTabGoodsQuery query);

}
