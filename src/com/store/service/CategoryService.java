package com.store.service;

import com.store.domain.Category;

import java.util.List;

/**
 * Created by Taeyeon-Serenity on 2017/7/25.
 */
public interface CategoryService {
    List<Category> findAll() throws Exception;
}
