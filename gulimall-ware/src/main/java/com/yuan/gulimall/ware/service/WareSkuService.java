package com.yuan.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuan.common.to.OrderTo;
import com.yuan.common.to.mq.StockLockedTo;
import com.yuan.common.utils.PageUtils;
import com.yuan.gulimall.ware.entity.WareSkuEntity;
import com.yuan.gulimall.ware.vo.SkuHasStockVo;
import com.yuan.gulimall.ware.vo.WareSkuLockVo;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author yuan
 * @email 1006969989@qq.com
 * @date 2023-10-17 16:09:30
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addStock(Long skuId, Long wareId, Integer skuNum);

    List<SkuHasStockVo> getSkuHasStock(List<Long> skuIds);
    /**
     * 锁定库存
     * @param vo
     * @return
     */
    boolean orderLockStock(WareSkuLockVo vo);

    void unlockStock(StockLockedTo to);
    /**
     * 解锁订单
     * @param orderTo
     */
    void unlockStock(OrderTo orderTo);
}

