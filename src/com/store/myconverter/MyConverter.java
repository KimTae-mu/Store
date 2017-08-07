package com.store.myconverter;

import org.apache.commons.beanutils.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Taeyeon-Serenity on 2017/7/24.
 */
public class MyConverter implements Converter {
    @Override
    public Object convert(Class clazz, Object value) {
        //class 要转成的类型
        //object 页面上传入的值

        //讲object 转成date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse((String) value);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
