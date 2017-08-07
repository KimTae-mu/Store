package com.store.dao.impl;

import com.store.dao.OrderDao;
import com.store.domain.Order;
import com.store.domain.OrderItem;
import com.store.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;



public class OrderDaoImpl implements OrderDao {

    /**
     * 添加一条订单
     */
    @Override
    public void add(Order order) throws Exception {
        QueryRunner qr = new QueryRunner();
		/*
		 * `oid` varchar(32) NOT NULL,
		  `ordertime` datetime DEFAULT NULL,
		  `total` double DEFAULT NULL,

		  `state` int(11) DEFAULT NULL,
		  `address` varchar(30) DEFAULT NULL,
		  `name` varchar(20) DEFAULT NULL,

		  `telephone` varchar(20) DEFAULT NULL,
		  `uid` varchar(32) DEFAULT NULL,
		 */
        System.out.println("OrderDaoImpl--add");
        String sql="insert into orders values(?,?,?,?,?,?,?,?)";
        qr.update(DataSourceUtils.getConnection(),sql, order.getOid(),order.getOrdertime(),order.getTotal(),order.getState(),
                order.getAddress(),order.getName(),order.getTelephone(),order.getUser().getUid());
    }

    /**
     * 添加一条订单项
     */
    @Override
    public void addItem(OrderItem oi) throws Exception {
        QueryRunner qr = new QueryRunner();
        /**
         * `itemid` varchar(32) NOT NULL,
         `count` int(11) DEFAULT NULL,
         `subtotal` double DEFAULT NULL,
         `pid` varchar(32) DEFAULT NULL,
         `oid` varchar(32) DEFAULT NULL,
         */
        System.out.println("OrderDaoImpl--addItem");

        String sql="insert into orderitem values(?,?,?,?,?)";
        qr.update(DataSourceUtils.getConnection(),sql, oi.getItemid(),oi.getCount(),oi.getSubtotal(),oi.getProduct().getPid(),oi.getOrder().getOid());
    }

}
