/*
 * Copyright (c) 2020.
 * author：qizuo
 */
package com.qizuo.util.parse;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * @Author :fangl
 * @Description: Properties文件读取和存储工具
 * @Date:14:12 2018/10/29
 */
public class PropertiesUtil {
    /**
     * @author: fangl
     * @description: 读取
     * @date: 15:47 2019/1/10
     */
    public static Map<String, String> read(String path) throws Exception {
        //返回一个map
        Map<String, String> backMap = new HashMap<>();

        Properties prop = new Properties();
        //读取属性文件a.properties
        InputStream in = new BufferedInputStream(new FileInputStream(path));
        //加载属性列表
        prop.load(in);
        //得到迭代器
        Iterator<String> it = prop.stringPropertyNames().iterator();
        while (it.hasNext()) {
            String key = it.next();
            backMap.put(key, prop.getProperty(key));
        }
        //关闭流
        in.close();
        return backMap;
    }

    /**
     * @author: fangl
     * @description: 存储
     * @date: 15:47 2019/1/10
     */
    public static void store(String path, String title, String name, String value) throws Exception {
        Properties prop = new Properties();
        //保存属性到b.properties文件
        //true表示追加打开
        FileOutputStream oFile = new FileOutputStream(path, true);
        prop.setProperty(name, value);
        prop.store(oFile, title);
        oFile.close();
    }

}
