package com.mypro.service;

import com.mypro.entity.MaskingPolicy;
import com.mypro.entity.User;
import com.mypro.entity.Role;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface MyService {
    public User queryUserByUserId(Integer UserId) throws Exception;
    public List<User> queryAllUser() throws Exception;
    public boolean validateUser(String username, String password) throws Exception;
    public boolean addUser(String email,String username,String password) throws Exception;
    public User addAnyUser(User user) throws Exception;
    public User updateUser(User user) throws Exception;
    public boolean deleteUser(Integer userId) throws Exception;

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    List<MaskingPolicy> queryAllPolicies() throws Exception;

    @Transactional(propagation = Propagation.REQUIRED)
    MaskingPolicy addPolicy(MaskingPolicy policy) throws Exception;

    @Transactional(propagation = Propagation.REQUIRED)
    MaskingPolicy updatePolicy(MaskingPolicy policy) throws Exception;

    @Transactional(propagation = Propagation.REQUIRED)
    boolean deletePolicy(Integer policyId) throws Exception;

//    //实现在SpringBoot中配置数据库事务操作
//    public boolean deleteDept(Integer deptno) throws Exception;


}
