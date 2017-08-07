package com.store.service.impl;

import com.store.dao.CategoryDao;
import com.store.dao.impl.CategoryDaoImpl;
import com.store.domain.Category;
import com.store.service.CategoryService;
import com.store.utils.BeanFactory;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Taeyeon-Serenity on 2017/7/25.
 */
public class CategoryServiceImpl implements CategoryService {
    /**
     * 查询所有的分类*/
    @Override
    public List<Category> findAll() throws Exception {
        //创建缓存管理器
        CacheManager cm=CacheManager.create(CategoryServiceImpl.class.getClassLoader().getResourceAsStream("ehcache.xml"));

        //获取指定的缓存
        Cache cache = cm.getCache("categoryCache");

        //通过缓存获取数据  讲cache看成一个map即可
        Element element = cache.get("clist");

        List<Category> list=null;
        //判断数据
        if(element==null){
            //从数据库中获取
            CategoryDao cd= (CategoryDao) BeanFactory.getBean("CategoryDao");
            list=cd.findAll();

            //将list放入缓存
            cache.put(new Element("clist",list));

            System.out.println("缓存中没有数据，已去数据库中获取");
        }else {
            //直接返回
            list= (List<Category>) element.getObjectValue();
            System.out.println("缓存中有数据");
        }

        return list;
    }

    public static void main(String[] args) {
        InputStream is = CategoryServiceImpl.class.getClassLoader().getResourceAsStream("ehcache.xml");
        System.out.println(is);
    }
}
