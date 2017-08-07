package com.store.domain;

import java.io.Serializable;

/**
 * Created by Taeyeon-Serenity on 2017/7/26.
 */
/**
 * 购物车项
 * */
public class CartItem implements Serializable {
    private Product product;
    private Integer count;
    private Double subtotal= 0.0;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getSubtotal() {
        return product.getShop_price()*count;
    }

    public CartItem() {}

    public CartItem(Product product, Integer count) {
        this.product = product;
        this.count = count;
    }
}
