package com.store.service;

import com.store.domain.PageBean;
import com.store.domain.Product;

import java.util.List;

/**
 * Created by Taeyeon-Serenity on 2017/7/25.
 */
public interface ProductService {
    List<Product> findNew() throws Exception;

    List<Product> findHot() throws Exception;


    Product getByPid(String pid) throws Exception;

    PageBean<Product> findByPage(int currPage, int pageSize, String cid) throws Exception;
}
