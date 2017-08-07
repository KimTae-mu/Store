package com.store.web.servlet;

import com.store.constant.Constant;
import com.store.domain.User;
import com.store.myconverter.MyConverter;
import com.store.service.UserService;
import com.store.service.impl.UserServiceImpl;
import com.store.utils.BeanFactory;
import com.store.utils.MD5Utils;
import com.store.utils.UUIDUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Taeyeon-Serenity on 2017/7/24.
 */
/**
 * 和用户相关的方法
 * */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("UserServlet的add方法执行了！！！");
        return null;
    }

    /**
     * 跳转到注册页面
     * */
    public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/jsp/register.jsp";
    }

    /**
     * 跳转到登陆页面
     * */
    public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/jsp/login.jsp";
    }

    /**
     * 用户注册
     * */
    public String regist(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //封装数据
        User user = new User();
        //注册自定义的转换器
        ConvertUtils.register(new MyConverter(), Date.class);
        BeanUtils.populate(user,request.getParameterMap());
        //设置用户id
        user.setUid(UUIDUtils.getId());
        //设置激活码
        user.setCode(UUIDUtils.getCode());
        //加密密码
        user.setPassword(MD5Utils.md5(user.getPassword()));

        //调用service完成注册
        UserService s= (UserService) BeanFactory.getBean("UserService");
        s.regist(user);
        //页面请求转发
        request.setAttribute("msg","用户注册已成功，前去邮箱激活");
        return "/jsp/msg.jsp";
    }

    /**
     * 用户激活
     * */
    public String active(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取激活码
        String code = request.getParameter("code");

        //调用service完成激活
        UserService s=(UserService) BeanFactory.getBean("UserService");
        User user=s.active(code);

        if(user==null){
            //通过激活码没有找到用户
            request.setAttribute("msg","请重新激活");
        }else {
            //添加信息
            request.setAttribute("msg","激活成功");
        }
        //请求转发到msg.jsp
        return "/jsp/msg.jsp";
    }

    /**
     * 用户登陆
     * */
    public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取用户名和密码
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        password=MD5Utils.md5(password);

        //调用service完成登陆操作返回user
        UserService s=(UserService) BeanFactory.getBean("UserService");
        User user=s.login(username,password);

        //判断用户
        if(user==null){
            //用户名密码不匹配
            request.setAttribute("msg","用户名密码不匹配");
            return "jsp/login.jsp";
        }else {
            //继续判断用户的状态是否激活
            if(Constant.USER_IS_ACTIVE != user.getState()){
                request.setAttribute("msg","用户未激活");
                return "/jsp/login.jsp";
            }
        }

        //将user放入session中重定向
        request.getSession().setAttribute("user",user);
        response.sendRedirect(request.getContextPath()+"/");
        return null;
    }

    /**
     * 用户退出
     * */
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //干掉session
        request.getSession().invalidate();

        //重定向
        response.sendRedirect(request.getContextPath());

        //处理自动登录
        return null;
    }

}

