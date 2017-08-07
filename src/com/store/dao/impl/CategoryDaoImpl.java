package com.store.dao.impl;

import com.store.dao.CategoryDao;
import com.store.domain.Category;
import com.store.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * Created by Taeyeon-Serenity on 2017/7/25.
 */
public class CategoryDaoImpl implements CategoryDao {
    /**
     * */
    @Override
    public List<Category> findAll() throws Exception {
        System.out.println("findAll--dao");
        QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
        String sql="select * from category";

        return qr.query(sql,new BeanListHandler<>(Category.class));
    }
}
