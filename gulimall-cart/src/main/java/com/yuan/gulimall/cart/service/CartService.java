package com.yuan.gulimall.cart.service;

import com.yuan.gulimall.cart.vo.CartItemVo;

import java.util.concurrent.ExecutionException;

public interface CartService {

    /**
     * 将商品添加至购物车
     * @param skuId
     * @param num
     * @return
     */
    CartItemVo addToCart(Long skuId, Integer num) throws ExecutionException, InterruptedException;

    CartItemVo getCartItem(Long skuId);
}
