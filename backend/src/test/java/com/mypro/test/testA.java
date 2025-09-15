package com.mypro.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mypro.entity.User;
import com.mypro.entity.Role;
import com.mypro.mapper.UserMapper;
import com.mypro.mapper.RoleMapper;
import com.mypro.service.MyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

//springboot单元测试
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class testA {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MyService myService;
    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void test1() throws Exception {
        myService.queryUserByUserId(5);
    }

    @Test
    public void test2() throws Exception {
        boolean testvali = myService.validateUser("user1","password1");
    }

}
