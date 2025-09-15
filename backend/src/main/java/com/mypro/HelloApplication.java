package com.mypro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//springboot的主启动 主配置类  会自动加载所在包以及其子包下所有的注解配置
@SpringBootApplication

@MapperScan(basePackages = {"com.mypro.mapper"} )


public class HelloApplication {
    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class,args);
    }
}
