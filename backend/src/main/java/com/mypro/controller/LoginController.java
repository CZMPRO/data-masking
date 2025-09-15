package com.mypro.controller;
import com.mypro.entity.User;
import com.mypro.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/home-page")

public class LoginController {
    @Autowired
    private MyService myService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) throws Exception {
        // 这里假设 User 对象包含 username 和 password 字段
        String username = user.getUsername();
        String password = user.getPassword();
        System.out.println(username);

        System.out.println(password);

        // 调用 service 层的方法来验证用户名和密码
        boolean isLoginSuccess = myService.validateUser(username, password);
        Map<String, Object> response = new HashMap<>();


        if (isLoginSuccess) {
            response.put("message", "Login Success");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Login Failed");
            return ResponseEntity.status(401).body(response);
        }
    }

    @PostMapping("/regist")
    public ResponseEntity<Map<String, Object>> regist(@RequestBody User user) throws Exception{
        String email = user.getEmail();
        String username = user.getUsername();
        String password = user.getPassword();
        // 调用 service 层的方法来验证用户名和密码
        boolean isRegistSuccess = myService.addUser(email,username, password);
        Map<String, Object> response = new HashMap<>();
        if (isRegistSuccess) {
            response.put("message", "Register Success");
            return ResponseEntity.ok(response);
        }else{
            response.put("message", "Register Failed");
            return ResponseEntity.status(401).body(response);
        }

    }

}


