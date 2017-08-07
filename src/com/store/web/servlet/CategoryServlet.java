package com.store.web.servlet;

import com.store.domain.Category;
import com.store.service.CategoryService;
import com.store.service.impl.CategoryServiceImpl;
import com.store.utils.BeanFactory;
import com.store.utils.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Taeyeon-Serenity on 2017/7/25.
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends BaseServlet {
    /**
     * 查询所有的分类
     * */
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //调用categoryservice 查询所有的分类 返回值list
        System.out.println("findAll--CategoryServlet");
        CategoryService cs= (CategoryService) BeanFactory.getBean("CategoryService");
        List<Category> clist = null;
        try {
            clist = cs.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 2.将返回值转成json格式 返回到页面上
        //request.setAttribute("clist", clist);
        String json = JsonUtil.list2json(clist);

        //3.写回去
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(json);

        return null;
    }
}
