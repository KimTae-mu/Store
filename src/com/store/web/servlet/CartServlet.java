package com.store.web.servlet;

import com.store.domain.Cart;
import com.store.domain.CartItem;
import com.store.domain.Product;
import com.store.service.ProductService;
import com.store.utils.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Taeyeon-Serenity on 2017/7/26.
 */
/**
 * 购物车模块
 * */
@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet {

    public Cart getCart(HttpServletRequest request){
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart==null){
            //创建一个cart
            cart=new Cart();
            //添加到session中
            request.getSession().setAttribute("cart",cart);
        }
        return cart;
    }

    /**
     * 添加到购物车
     * */
    public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取pid和count
        String pid = request.getParameter("pid");
        int count = Integer.parseInt(request.getParameter("count"));

        //通过pid 调用ProductService获取一个商品
        ProductService ps= (ProductService) BeanFactory.getBean("ProductService");
        Product product = ps.getByPid(pid);

        //组装成CartItem
        CartItem cartItem = new CartItem(product,count);

        //添加到购物车中
        Cart cart = getCart(request);
        cart.add2Cart(cartItem);

        //重定向
        response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");

        return null;
    }

    /**
     * 从购物车中移除购物车项
     * */
    public String remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取商品的pid
        String pid = request.getParameter("pid");

        //调用购物车的remove方法
        getCart(request).removeFromCart(pid);

        //重定向
        response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
        return null;
    }

    /**
     * 清空购物车
     * */
    public String clear(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取购物车 清空
        getCart(request).clearCart();
        response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");

        return null;
    }
}
