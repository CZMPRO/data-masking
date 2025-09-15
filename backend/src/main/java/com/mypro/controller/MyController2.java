//package com.mypro.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping(value = {"/mycontroller2"})
//public class MyController2 {
//    //请求转发或响应重定向跳转到其他Controller或页面
//
//    @RequestMapping(value = {"/method1"})
//    public String method1()throws Exception{
//        System.out.println("mycontroller2 method1");
//        //请求转发到页面    路径不变
//        return "forward:/add.html";
//    }
//    @RequestMapping(value = {"/method2"})
//    public String method2()throws Exception{
//        System.out.println("mycontroller2 method2");
//        //响应重定向到页面  路径变化
//        return "redirect:/add.html";
//    }
//
//    @RequestMapping(value = {"/method3"})
//    public String method3()throws Exception{
//        System.out.println("mycontroller2 method1");
//        //请求转发到其他Controller    路径不变
//        return "forward:/mycontroller1/method1";
//    }
//    @RequestMapping(value = {"/method4"})
//    public String method4()throws Exception{
//        System.out.println("mycontroller2 method2");
//        //响应重定向到其他Controller  路径变化
//        return "redirect:/mycontroller1/method1";
//    }
//}
