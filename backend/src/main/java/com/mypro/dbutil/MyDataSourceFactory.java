package com.mypro.dbutil;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;


public class MyDataSourceFactory extends UnpooledDataSourceFactory {
    public MyDataSourceFactory() {
        this.dataSource = new DruidDataSource();
    }
}
