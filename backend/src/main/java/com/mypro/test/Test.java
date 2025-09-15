package com.mypro.test;

import com.mypro.dbutil.DbUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        SqlSession session = DbUtil.getSession();
//        EmpMapper empMapper = session.getMapper(EmpMapper.class);
//        DeptMapper deptMapper = session.getMapper(DeptMapper.class);
//
//        //查询所有
////        List<Emp> empList =empMapper.selectByExample(null);
////        System.out.println(empList);
//
//        //查询单个
////        Dept dept=  deptMapper.selectByPrimaryKey(10);
////        System.out.println(dept);
//
//        //添加
////        Dept  dept = new Dept();//deptno设置了自动增长，不考虑deptno
////        dept.setDeptName("mydept");
////        dept.setLoc("myloc");
////        Integer  result = deptMapper.insert(dept);
////        System.out.println(result);
//
//        //修改
////        Dept  dept = new  Dept();
////        dept.setDeptno(51);
////        dept.setDeptName("newDept");
////        dept.setLoc("newLOc");
////        deptMapper.updateByPrimaryKey(dept);//id为条件修改其他所有列
//
//
////        Dept  dept = new  Dept();
////        dept.setDeptno(51);
////        dept.setDeptName("MY_DEPT");
////        deptMapper.updateByPrimaryKeySelective(dept);//根据ID为条件 修改设置了的属性列
//
//        //删除
////        System.out.println(deptMapper.deleteByPrimaryKey(51));
//
//        //带条件查询
////        EmpExample e=  new EmpExample();
////        e.createCriteria().andJobEqualTo("SALESMAN")
////                          .andDeptnoEqualTo(20);//通过Exampler对象编辑查询条件
////        List<Emp> empList = empMapper.selectByExample(e);
////        System.out.println(empList);
//
//        //or
//        EmpExample e=  new EmpExample();
//        //1   where job='xxx' or deptno=xx or sal>1000
////        e.createCriteria().andJobEqualTo("SALESMAN");
////        EmpExample.Criteria or1 = e.createCriteria().andDeptnoEqualTo(20);
////        EmpExample.Criteria or2 = e.createCriteria().andSalGreaterThan(1000.0f);
////        e.or(or1);
////        e.or(or2);
//
//
//
//        //2
//        e.createCriteria().andJobEqualTo("SALESMAN");
//        e.or().andDeptnoEqualTo(20);
//        e.or().andSalLessThan(3000.0f);
//
//        List<Emp> empList = empMapper.selectByExample(e);
//        System.out.println(empList);

        session.commit();
        session.close();

    }
}
