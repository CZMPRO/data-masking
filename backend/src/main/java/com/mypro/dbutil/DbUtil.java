package com.mypro.dbutil;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

//获得MyBatis下的操作工具  SqlSession(替代jdbc的connection+PreparedStatement)
public class DbUtil {



    public static SqlSession getSession(){
        try{
            //读取configuration主配置文件，获取SqlSession对象
            Reader reader = Resources.getResourceAsReader("configuration.xml");
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(reader);
            SqlSession session = factory.openSession();
            return  session;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
