package com.store.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Taeyeon-Serenity on 2017/7/26.
 */
public class Cart implements Serializable {
    //存放购物车项的集合 key:商品的id cartitem:购物车项 使用map集合便于删除单个购物车项
    private Map<String,CartItem> map=new LinkedHashMap<>();
    //总金额
    private Double total=0.0;

    /**
     * 获取所有的购物车项
     * */
    public Collection<CartItem> getItems(){
        return map.values();
    }

    /**
     * 添加到购物车
     * */
    public void add2Cart(CartItem item){
        //先判断购物车中有无该商品
        //先获取商品的id
        String pid = item.getProduct().getPid();
        if(map.containsKey(pid)){
            //设置购买数量 需要获取该商品之前的购买数量+现在的购买数量
            CartItem oItem = map.get(pid);
            oItem.setCount(oItem.getCount()+item.getCount());
        }else {
            //将购物车项添加进去
            map.put(pid,item);
        }
        //添加完成之后 修改金额
        total+=item.getSubtotal();
    }

    /**
     * 从购物车删除指定的购物车项
     * */
    public void removeFromCart(String pid){
        //从集合中删除
        CartItem item = map.remove(pid);

        //修改金额
        total-=item.getSubtotal();
    }

    /**
     * 清空购物车
     * */
    public void clearCart(){
        //map置空
        map.clear();

        //金额归零
        total=0.0;
    }

    public Map<String, CartItem> getMap() {
        return map;
    }

    public void setMap(Map<String, CartItem> map) {
        this.map = map;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}
