package com.store.service.impl;

import com.store.dao.UserDao;
import com.store.dao.impl.UserDaoImpl;
import com.store.domain.User;
import com.store.service.UserService;
import com.store.utils.BeanFactory;
import com.store.utils.MailUtils;

/**
 * Created by Taeyeon-Serenity on 2017/7/24.
 */
public class UserServiceImpl implements UserService {
    @Override
    public void regist(User user) throws Exception {
        UserDao dao= (UserDao) BeanFactory.getBean("UserDao");
        dao.add(user);
        //发送激活邮件
        //email：收件人的地址 emailMsg：邮件的内容
        String emailMsg="欢迎您注册成我们的一员，<a href='http://localhost:8080/Store/UserServlet?method=active&code="+user.getCode()+"'>点此激活</a>";
        MailUtils.sendMail(user.getEmail(),emailMsg);
    }

    /**
     * 用户激活
     * */
    @Override
    public User active(String code) throws Exception {
        UserDao dao=(UserDao) BeanFactory.getBean("UserDao");
        //通过code获取一个用户
        User user=dao.getByCode(code);

        //判断用户是否为空
        if(user==null){
            return null;
        }

        //修改用户状态
        //设置用户的状态码
        user.setState(1);
        dao.update(user);

        return user;
    }

    /**
     * 用户登录
     * */
    @Override
    public User login(String username, String password) throws Exception {
        UserDao dao=(UserDao) BeanFactory.getBean("UserDao");
        return dao.getByUserNameAndPwd(username,password);
    }
}
