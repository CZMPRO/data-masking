package com.mypro.controller;

import com.mypro.entity.User;
import com.mypro.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-page")

public class UserController {
    @Autowired
    private MyService myService;
    // 获取所有用户
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() throws Exception{
        List<User> usersList = myService.queryAllUser();
        return ResponseEntity.ok(usersList);
    }

    // 添加用户
    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) throws Exception {
        user.setRoleId(1);
        user.setPassword("123456@User");
        User addedUser = myService.addAnyUser(user);
        return ResponseEntity.ok(addedUser);
    }

    // 更新用户
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) throws Exception {
        user.setUserId(id);
        User updatedUser = myService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    // 删除用户
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) throws Exception {
        myService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
