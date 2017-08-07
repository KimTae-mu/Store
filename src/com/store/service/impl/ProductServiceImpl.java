package com.store.service.impl;

import com.store.dao.ProductDao;
import com.store.dao.impl.ProductDaoImpl;
import com.store.domain.PageBean;
import com.store.domain.Product;
import com.store.service.ProductService;
import com.store.utils.BeanFactory;

import java.util.List;

/**
 * Created by Taeyeon-Serenity on 2017/7/25.
 */
public class ProductServiceImpl implements ProductService {
    /**
     * 查找最新商品信息
     * */
    @Override
    public List<Product> findNew() throws Exception {
        ProductDao pdao= (ProductDao) BeanFactory.getBean("ProductDao");
        return pdao.findNew();
    }

    /**
     * 查找热门商品信息
     * */
    @Override
    public List<Product> findHot() throws Exception {
        ProductDao pdao=(ProductDao) BeanFactory.getBean("ProductDao");
        return pdao.findHot();
    }

    /**
     * 查询单个商品
     * */
    @Override
    public Product getByPid(String pid) throws Exception {
        ProductDao pdao=(ProductDao) BeanFactory.getBean("ProductDao");
        return pdao.getByPid(pid);
    }

    /**
     * 按类别分页查询商品
     * ‘*/
    @Override
    public PageBean<Product> findByPage(int currPage, int pageSize, String cid) throws Exception {
        ProductDao pdao=(ProductDao) BeanFactory.getBean("ProductDao");
        //当前页数据
        List<Product> list=pdao.findByPage(currPage,pageSize,cid);

        //总条数
        int totalCount=pdao.getTotalCount(cid);

        return new PageBean<>(list,currPage,pageSize,totalCount);
    }
}
