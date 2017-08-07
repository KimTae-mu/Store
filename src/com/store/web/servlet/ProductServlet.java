package com.store.web.servlet;

import com.store.domain.PageBean;
import com.store.domain.Product;
import com.store.service.ProductService;
import com.store.service.impl.ProductServiceImpl;
import com.store.utils.BeanFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Taeyeon-Serenity on 2017/7/25.
 */
/**
 * 商品Servlet
 * */
@WebServlet("/ProductServlet")
public class ProductServlet extends BaseServlet {
    /**
     * 通过id查询单个商品详情
     * */
    public String getById(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取商品的id
        String pid=request.getParameter("pid");

        //调用service
        ProductService ps= (ProductService) BeanFactory.getBean("ProductService");
        Product p=ps.getByPid(pid);

        //将结果放入request中 请求转发
        request.setAttribute("bean",p);

        return "/jsp/product_info.jsp";
    }

    /**
     * 分页查询数据
     * */
    public String findByPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取类别 当前页 设置一个pagesize
        String cid=request.getParameter("cid");
        int currPage= Integer.parseInt(request.getParameter("currPage"));
        int pageSize=12;

        //调用service 返回值pagebean
        ProductService ps= (ProductService) BeanFactory.getBean("ProductService");
        PageBean<Product> bean=ps.findByPage(currPage,pageSize,cid);

        //将结果放入request中 请求转发
        request.setAttribute("pb",bean);
        return "/jsp/product_list.jsp";
    }
}
