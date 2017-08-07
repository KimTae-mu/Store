package com.store.dao;

import com.store.domain.Category;

import java.util.List;

/**
 * Created by Taeyeon-Serenity on 2017/7/25.
 */
public interface CategoryDao {
    List<Category> findAll() throws Exception;
}
