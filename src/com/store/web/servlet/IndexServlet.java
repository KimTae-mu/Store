package com.store.web.servlet;

import com.store.domain.Category;
import com.store.domain.Product;
import com.store.service.CategoryService;
import com.store.service.ProductService;
import com.store.service.impl.CategoryServiceImpl;
import com.store.service.impl.ProductServiceImpl;
import com.store.utils.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Taeyeon-Serenity on 2017/7/24.
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends BaseServlet {
    public String index(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("indexServlet--1");
        //去数据库中查询最新商品和热门商品，将他们放入request域中 请求转发
        ProductService ps= (ProductService) BeanFactory.getBean("ProductService");


        List<Product> newList= null;
        List<Product> hotList=null;
        try {
            //最新商品
            newList = ps.findNew();
            //热门商品
            hotList=ps.findHot();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //将两个list放入域中
        request.setAttribute("nList",newList);
        request.setAttribute("hList",hotList);

        return "/jsp/index.jsp";
    }
}
