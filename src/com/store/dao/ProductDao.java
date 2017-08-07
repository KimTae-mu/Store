package com.store.dao;

import com.store.domain.Product;

import java.util.List;

/**
 * Created by Taeyeon-Serenity on 2017/7/25.
 */
public interface ProductDao {
    List<Product> findNew() throws Exception;

    List<Product> findHot() throws Exception;

    Product getByPid(String pid) throws Exception;

    List<Product> findByPage(int currPage, int pageSize, String cid) throws Exception;

    int getTotalCount(String cid) throws Exception;
}
