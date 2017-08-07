package com.store.dao;

import com.store.domain.Order;
import com.store.domain.OrderItem;

/**
 * Created by Taeyeon-Serenity on 2017/7/26.
 */
public interface OrderDao {
    void add(Order order) throws Exception;

    void addItem(OrderItem oi) throws Exception;
}
