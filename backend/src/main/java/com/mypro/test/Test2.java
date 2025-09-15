package com.mypro.test;

import com.mypro.dbutil.DbUtil;
import com.mypro.entity.User;
import com.mypro.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;

public class Test2 {
    public static void main(String[] args) {
        SqlSession session = DbUtil.getSession();
//        EmpMapper empMapper = session.getMapper(EmpMapper.class);
//        DeptMapper deptMapper = session.getMapper(DeptMapper.class);

          UserMapper userMapper = session.getMapper(UserMapper.class);
          User user = userMapper.selectByPrimaryKey(5);
          System.out.println(user);
          System.out.println(user.getUserId());
//
//        Dept dept = deptMapper.selectByPrimaryKey(20);
//        System.out.println(dept);
//        System.out.println(dept.getEmps());
//
//        //分页助手
//        PageHelper.startPage(2,2);//指定分页规则  每页显示2行，当前显示第二页
//        Page<Emp> page = (Page<Emp>) empMapper.selectByExample(null);
//        List<Emp> empList = page.getResult();
//        for (Emp e: empList){
//            System.out.println(e);
//
//        }

        session.close();
    }
}
