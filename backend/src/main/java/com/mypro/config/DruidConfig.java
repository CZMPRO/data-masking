package com.mypro.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//放在主启动类所在包及所有子包之下，注解配置都会被加载
@Configuration
//mapper扫描
@MapperScan(basePackages = {"com.mypro.mapper"})
public class DruidConfig {
    //生成Druid连接池时 会加载所有spring.datasource开头的配置
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean//在spring容器中 声明创建一个对象
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }
}
