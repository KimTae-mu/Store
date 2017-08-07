package com.store.web.servlet;

import com.store.domain.*;
import com.store.service.OrderService;
import com.store.utils.BeanFactory;
import com.store.utils.UUIDUtils;
import com.store.web.servlet.BaseServlet;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 订单模块
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {

    /**
     * 生成订单
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //0.判断用户是否登录
        User user=(User) request.getSession().getAttribute("user");
        if(user == null){
            request.setAttribute("msg", "请先登录~~");
            return "/jsp/msg.jsp";
        }

        //1.封装数据
        Order order=new Order();
        //1.1 订单id
        order.setOid(UUIDUtils.getId());

        //1.2 订单时间
        order.setOrdertime(new Date());

        //1.3 总金额
        //获取session中cart
        Cart cart=(Cart) request.getSession().getAttribute("cart");

        order.setTotal(cart.getTotal());

        //1.4 订单的所有订单项
		/*
		 * 先获取cart中itmes
		 * 遍历itmes 组装成orderItem
		 * 将orderItem添加到list(items)中
		 */
        for (CartItem cartItem : cart.getItems()) {
            OrderItem oi = new OrderItem();

            //设置id
            oi.setItemid(UUIDUtils.getId());

            //设置购买数量
            oi.setCount(cartItem.getCount());

            //设置小计
            oi.setSubtotal(cartItem.getSubtotal());

            //设置product
            oi.setProduct(cartItem.getProduct());

            //设置order
            oi.setOrder(order);

            //添加到list中
            order.getItems().add(oi);
        }

        //1.5 设置用户
        order.setUser(user);

        //2.调用service 添加订单
        OrderService os=(OrderService) BeanFactory.getBean("OrderService");
        os.add(order);

        //3.将order放入request域中,请求转发
        request.setAttribute("bean", order);

        //4.清空购物车
        request.getSession().removeAttribute("cart");
        return "/jsp/order_info.jsp";
    }

}
