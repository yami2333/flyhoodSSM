package com.neuedu.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionUtils {
    private static SqlSessionUtils sqlSessionUtils;
    private SqlSessionUtils(){}
    public static SqlSessionUtils getInstance(){
        if(sqlSessionUtils == null){
            sqlSessionUtils = new SqlSessionUtils();
        }
        return sqlSessionUtils;
    }
    public SqlSession getSqlSession(){
        InputStream inputStream = null;
        SqlSessionFactory sqlSessionFactory = null;
        SqlSession sqlSession = null;
        try {
            inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 1.2创建sqlSession对象 -- 代理对象
        sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }
}
