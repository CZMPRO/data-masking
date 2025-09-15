package com.mypro.service.impl;


import com.mypro.entity.MaskingPolicy;
import com.mypro.entity.User;
import com.mypro.mapper.MaskingPolicyMapper;
import com.mypro.mapper.UserMapper;
import com.mypro.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;

@Service //所有Service层的类 必须使用的注解，以标识在Spring容器中创建该类对象
public class MyServiceImpl implements MyService {

    //检验邮箱
    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.matches(regex, email);
    }
    //检验用户名
    private boolean isValidUsername(String username) {
        String regex = "^[a-zA-Z0-9_]+$";
        return Pattern.matches(regex, username);
    }
    //检验密码
    private boolean isValidPassword(String password) {
        String regex = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,}$";
        return Pattern.matches(regex, password);
    }

    @Autowired
    private UserMapper userMapper;
    @Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
    @Override
    public User queryUserByUserId(Integer userId) throws Exception{
        //调用一个mapper方法 也需要封装成一个service方法
        return this.userMapper.selectByPrimaryKey(userId);
    }

    public User queryUserByUsername(String username) throws Exception{
        //调用一个mapper方法 也需要封装成一个service方法
        List<User> userList = userMapper.selectByUsername(username);
        User user = userList.get(0);
        return user;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
    @Override
    public boolean validateUser(String username, String password) throws Exception{
        // 这里实现具体的验证逻辑，比如查询数据库
        // 假设有一个方法可以根据用户名获取用户信息
        List<User> userFromDB = userMapper.selectByUsername(username);

        for(User user : userFromDB){
            if(user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
    @Override
    public boolean addUser(String email,String username,String password) throws Exception {
        // 检查邮箱格式
        if (!isValidEmail(email)) {
            throw new Exception("邮箱格式不正确");
        }

        // 检查用户名长度和内容
        if (username.length() < 4 || username.length() > 20) {
            throw new Exception("用户名长度应在4-20个字符之间");
        }
        if (!isValidUsername(username)) {
            throw new Exception("用户名只能包含字母、数字和下划线");
        }

        // 检查密码强度
        if (!isValidPassword(password)) {
            throw new Exception("密码应至少包含8个字符，包含字母、数字和特殊字符至少一种");
        }

        System.out.println(userMapper.selectByUsername(username));
        System.out.println(userMapper.selectByEmail(email));
        //检验重复注册
        if (userMapper.selectByUsername(username) != null || userMapper.selectByEmail(email) != null) {
            throw new Exception("用户名或邮箱已存在");
        }

        //保存到数据库
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        user.setRoleId(4);
        userMapper.Myinsert(user);

        return true;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
    @Override
    public List<User> queryAllUser() throws Exception{
        return this.userMapper.selectByExample(null);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
    @Override
    public User addAnyUser(User user) throws Exception {
        String email = user.getEmail();
        String username = user.getUsername();
        String password = user.getPassword();
        System.out.println(email);
        System.out.println(username);
        System.out.println(password);

        // 检查邮箱格式
        if (!isValidEmail(email)) {
            throw new Exception("邮箱格式不正确");
        }

        // 检查用户名长度和内容
        if (username.length() < 4 || username.length() > 20) {
            throw new Exception("用户名长度应在4-20个字符之间");
        }
        if (!isValidUsername(username)) {
            throw new Exception("用户名只能包含字母、数字和下划线");
        }

        // 检查密码强度
        if (!isValidPassword(password)) {
            throw new Exception("密码应至少包含8个字符，包含字母、数字和特殊字符至少一种");
        }
        System.out.println("Test:");
        System.out.println(userMapper.selectByUsername(username));
        System.out.println(userMapper.selectByEmail(email));

        // 检验重复注册
        List<User> existingUsersByUsername = userMapper.selectByUsername(username);
        List<User> existingUsersByEmail = userMapper.selectByEmail(email);

        System.out.println("Existing users by username: " + existingUsersByUsername);
        System.out.println("Existing users by email: " + existingUsersByEmail);

        if (!existingUsersByUsername.isEmpty()) {
            System.out.println("Found existing user(s) by username: " + existingUsersByUsername);
            throw new Exception("用户名或邮箱已存在");
        } else if (!existingUsersByEmail.isEmpty()) {
            System.out.println("Found existing user(s) by email: " + existingUsersByEmail);
            throw new Exception("用户名或邮箱已存在");
        } else {
            // 设置 role_id 默认值为 1
            user.setRoleId(1);
            userMapper.Myinsert(user);
            return user;
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
    @Override
    public User updateUser(User user) throws Exception{
        int result = userMapper.updateByPrimaryKey(user);
        if (result != 1) {
            throw new Exception("Update Failed<UNK>");
        }else {
            return user;
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
    @Override
    public boolean deleteUser(Integer userId) throws Exception{
        int result = userMapper.deleteByPrimaryKey(userId);
        if (result != 1) {
            throw new Exception("Delete Failed<UNK>");
        }else{
            return true;
        }
    }

    @Autowired
    private MaskingPolicyMapper maskingPolicyMapper;

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    @Override
    public List<MaskingPolicy> queryAllPolicies() throws Exception {
        return maskingPolicyMapper.selectByExample(null);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public MaskingPolicy addPolicy(MaskingPolicy policy) throws Exception {
        System.out.println("policy: " + policy);
        // 检查策略名称是否为空
        if (policy.getPolicyName() == null || policy.getPolicyName().trim().isEmpty()) {
            throw new Exception("策略名称不能为空");
        }
        // 检查目标字段是否为空
        if (policy.getTargetField() == null || policy.getTargetField().trim().isEmpty()) {
            throw new Exception("目标字段不能为空");
        }
        // 检查脱敏规则是否为空
        if (policy.getRule() == null || policy.getRule().trim().isEmpty()) {
            throw new Exception("脱敏规则不能为空");
        }

        int result = maskingPolicyMapper.insert(policy);
        if (result != 1) {
            throw new Exception("添加策略失败");
        } else {
            return policy;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public MaskingPolicy updatePolicy(MaskingPolicy policy) throws Exception {
        // 检查策略ID是否有效
        if (policy.getPolicyId() == null || policy.getPolicyId() <= 0) {
            throw new Exception("策略ID无效");
        }
        // 检查策略名称是否为空
        if (policy.getPolicyName() == null || policy.getPolicyName().trim().isEmpty()) {
            throw new Exception("策略名称不能为空");
        }
        // 检查目标字段是否为空
        if (policy.getTargetField() == null || policy.getTargetField().trim().isEmpty()) {
            throw new Exception("目标字段不能为空");
        }
        // 检查脱敏规则是否为空
        if (policy.getRule() == null || policy.getRule().trim().isEmpty()) {
            throw new Exception("脱敏规则不能为空");
        }

        int result = maskingPolicyMapper.updateByPrimaryKey(policy);
        if (result != 1) {
            throw new Exception("更新策略失败");
        } else {
            return policy;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deletePolicy(Integer policyId) throws Exception {
        // 检查策略ID是否有效
        if (policyId == null || policyId <= 0) {
            throw new Exception("策略ID无效");
        }

        int result = maskingPolicyMapper.deleteByPrimaryKey(policyId);
        if (result != 1) {
            throw new Exception("删除策略失败");
        } else {
            return true;
        }
    }

}
