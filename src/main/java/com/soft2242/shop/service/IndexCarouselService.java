package com.soft2242.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.soft2242.shop.entity.IndexCarousel;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wg-233
 * @since 2023-11-07
 */
public interface IndexCarouselService extends IService<IndexCarousel> {
    /**
     * 获取指定分布点的轮播图列表
     *
     * @param distributionSite 分布点的标识
     * @return 分布点对应的轮播图列表
     */
    List<IndexCarousel> getList(Integer distributionSite);
}
