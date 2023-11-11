package com.soft2242.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft2242.shop.entity.IndexCarousel;
import com.soft2242.shop.mapper.IndexCarouselMapper;
import com.soft2242.shop.service.IndexCarouselService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wg-233
 * @since 2023-11-07
 */
@Service
public class IndexCarouselServiceImpl extends ServiceImpl<IndexCarouselMapper, IndexCarousel> implements IndexCarouselService {

    @Override
    public List<IndexCarousel> getList(Integer distributionSite) {
        LambdaQueryWrapper<IndexCarousel> wrapper = new LambdaQueryWrapper<>();
        if (distributionSite!= null) {
            wrapper.eq(IndexCarousel::getType, distributionSite);
        }
        wrapper.orderByDesc(IndexCarousel::getCreateTime);
        List<IndexCarousel> list = baseMapper.selectList(wrapper);
        return null;
    }
}
