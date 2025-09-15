//package com.mypro.controller;
//
//import com.myspring.entity.Dept;
//import com.myspring.entity.Emp;
//import com.myspring.service.MyService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.List;
//
//@Controller
//@RequestMapping(value = {"/mycontroller3"})
//public class MyController3 {
//    //在controller中调用service
//    @Autowired
//    private MyService myService;
//
//    @RequestMapping(value = {"/method1"})
//    @ResponseBody
//    public String method1() throws Exception{
//        Dept dept = this.myService.queryDeptByDeptno(20);
//        return "<h1>"+dept+"</h1>";
//    }
//
//    @RequestMapping(value = {"/method2"})
//    @ResponseBody
//    public String method2() throws Exception{
//        List<Emp> empList = this.myService.queryAllEmp();
//        for(Emp emp : empList){
//            System.out.println(emp);
//        }
//        return "<h1>emplist</h1>";
//    }
//
//    @RequestMapping(value = {"/method3"})
//    @ResponseBody
//    public String method3() throws Exception{
//        boolean result = this.myService.deleteDept(10);
//        return "<h1>"+result+"</h1>";
//    }
//
//    @RequestMapping(value = {"/errortest1"})
//    @ResponseBody
//    public String errortest1() throws Exception{
//        System.out.println(1/0);
//        return "<h1>errortest1</h1>";
//    }
//
//    @RequestMapping(value = {"/errortest2"})
//    @ResponseBody
//    public String errortest2() throws Exception{
//        String str = null;
//        System.out.println(str.length());
//        return "<h1>errortest2</h1>";
//    }
//}
