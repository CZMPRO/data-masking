//package com.mypro.controller;
//
//import com.mypro.entity.User;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//
////Controller 替代javaweb中的servlet
//@Controller  //在springboot环境下 声明一个springmvc的controller
//@RequestMapping(value = {"/mycontroller1"})
//public class MyController1 {
//    //可以定义多个类似于Servlet中的service方法
//
//    @RequestMapping(value = {"/method1"},produces = {"text/html;charset=utf-8"})
//                        //配置该方法的访问路径,设置响应的中文编码
//    @ResponseBody//将返回值String输出到浏览器
//    public String method1()throws  Exception{
//        return "<h1>mycontroller1 method1 你好</h1>";
//    }
//
//    //在方法的参数中声明 请求对象与响应对象
//    @RequestMapping(value = {"/method2"})
//    public void method2(HttpServletRequest request, HttpServletResponse response) throws Exception{
//        response.setContentType("text/html;charset=utf-8");
//        response.getWriter().println("<h1>mycontroller1 method2 你好</h1>");
//    }
//
//    //使用controller去接收表单参数的多种做法
//    @RequestMapping(value = {"/login1"},produces = {"text/html;charset=utf-8"})
//    @ResponseBody
//    public String login1(HttpServletRequest request, HttpServletResponse response) throws  Exception{
//        //借助request 接收表单参数
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        return "<h1>"+username+"  "+password+"</h1>";
//    }
//
//    @RequestMapping(value = {"/login2"},produces = {"text/html;charset=utf-8"})
//    @ResponseBody
//    public String login2(String username,String password,Integer age) throws  Exception{
//        //直接在方法中声明 与表单参数同名的方法参数 自动接收数据以及类型转换
//        return "<h1>"+username+"  "+password+"  "+age+"</h1>";
//    }
//
//    @RequestMapping(value = {"/add"},produces = {"text/html;charset=utf-8"})
//    @ResponseBody
//    public String add(User user,HttpServletRequest request,HttpServletResponse response) throws  Exception{
//        //使用对象接收表单参数，要求对象的属性名与表单参数同名
//        return "<h1>"+user.toString()+"</h1>";
//    }
//
//    //springboot 中接收上传文件为 MultipartFile
//    @RequestMapping(value = {"/upload"})
//    public void upload(@RequestParam(value = "files") MultipartFile[] multipartFiles,//接收文件
//                       String desc,//代表其他表单元素
//                       HttpServletRequest request, HttpServletResponse response) throws  Exception{
//        System.out.println("desc:"+desc);//普通的请求参数
//
//        //MultipartFile 表示接收请求中包含的文件对象   UUID
//        for(MultipartFile multipartFile :multipartFiles){
//            //源文件名与文件类型
//            System.out.println(multipartFile.getOriginalFilename()+" "+multipartFile.getContentType());
//            //将文件内容保存到指定目录下  在webapp下建立upload文件夹
//            String path = request.getServletContext().getRealPath("upload");//获取文件夹的绝对路径
//            path = path+"/"+multipartFile.getOriginalFilename();
//            System.out.println(path);
//            File newFile = new File(path);//创建新文件
//            multipartFile.transferTo(newFile);//文件传输:将上传文件的内容写入到新文件
//        }
//
//
//    }
//
//}
