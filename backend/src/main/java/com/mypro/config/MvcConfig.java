//package com.mypro.config;
//
//import com.mypro.interceptor.MyInterceptor;
//import com.mypro.interceptor.OtherInterceptor;
//import org.springframework.boot.web.servlet.ServletComponentScan;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//
////springmvc interceptor拦截配置
//@Configuration
////sevletapi相关组件   filter  listener
//@ServletComponentScan(value = {"com.myspring.filter"})
//public class MvcConfig implements WebMvcConfigurer {
//    //使用java配置  用一个返回对象的方法作为配置使用
//    //在spring容器中 声明Interceptor对象
//    @Bean
//    public MyInterceptor getMyInterceptor() {
//        return new MyInterceptor();
//    }
//    @Bean
//    public OtherInterceptor getOtherInterceptor() {
//        return new OtherInterceptor();
//    }
//
//
//    //完成针对interceptor的配置 配置哪些controller需要interceptor
//    @Bean
//    public MvcConfig getMyWebMvcConfig(){
//        MvcConfig myWebMvcConfig = new MvcConfig() {
//            @Override
//            public void addInterceptors(InterceptorRegistry registry) {//注册拦截器
//                registry.addInterceptor(getMyInterceptor()).addPathPatterns("/**")
//                        .excludePathPatterns("/xxx/*");
//                registry.addInterceptor(getOtherInterceptor()).addPathPatterns("/mycontroller3/**")
//                        .excludePathPatterns("/xxx/*");
//            }
//        };
//        return myWebMvcConfig;
//    }
//
//}
