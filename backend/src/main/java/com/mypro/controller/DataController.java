package com.mypro.controller;

import com.mypro.entity.User;
import com.mypro.entity.SensitiveData;
import com.mypro.service.Masking_API;
import com.mypro.service.MyService;
import com.mypro.service.impl.MyServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/data-page")
public class DataController {

    @Autowired
    private Masking_API maskingService;

    @Autowired
    private MyServiceImpl myService;

    // 从请求头获取当前用户的用户名
    private String getCurrentUsername(HttpServletRequest request) {
        return request.getHeader("X-Username");
    }

    // 根据用户名获取用户信息
    private User getUserByUsername(String username) {
        try {
            return myService.queryUserByUsername(username);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 查询所有敏感数据（根据用户权限返回脱敏后的结果）
    @GetMapping("/list")
    public ResponseEntity<List<SensitiveData>> getAllSensitiveData(HttpServletRequest request) {
        String username = getCurrentUsername(request);
        System.out.println("Now username:" + username);
        User user = getUserByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            List<SensitiveData> data = maskingService.queryAllSensitiveDataForUser(user);
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    // 添加敏感数据
//    @PostMapping("/create")
//    public ResponseEntity<SensitiveData> addSensitiveData(HttpServletRequest request, @RequestBody SensitiveData sensitiveData) {
//        String username = getCurrentUsername(request);
//        User user = getUserByUsername(username);
//        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//        sensitiveData.setUploaderId(user.getRoleId()); // 设置上传者ID为当前用户的角色ID
//        try {
//            SensitiveData data = maskingService.addSensitiveData(sensitiveData);
//            return new ResponseEntity<>(data, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    // 根据ID获取敏感数据详情
//    @GetMapping("/{id}")
//    public ResponseEntity<SensitiveData> getSensitiveDataById(HttpServletRequest request, @PathVariable Integer id) {
//        String username = getCurrentUsername(request);
//        User user = getUserByUsername(username);
//        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//        try {
//            List<SensitiveData> data = maskingService.queryAllSensitiveDataForUser(user);
//            return new ResponseEntity<>(data.stream().filter(d -> d.getDataId().equals(id)).findFirst().orElse(null), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    // 更新敏感数据
//    @PutMapping("/update")
//    public ResponseEntity<SensitiveData> updateSensitiveData(HttpServletRequest request, @RequestBody SensitiveData sensitiveData) {
//        String username = getCurrentUsername(request);
//        User user = getUserByUsername(username);
//        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//        try {
//            return new ResponseEntity<>(maskingService.updateSensitiveData(sensitiveData), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    // 删除敏感数据
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Boolean> deleteSensitiveData(HttpServletRequest request, @PathVariable Integer id) {
//        String username = getCurrentUsername(request);
//        User user = getUserByUsername(username);
//        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//        try {
//            return new ResponseEntity<>(maskingService.deleteSensitiveData(id), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    // 一键脱敏所有数据
//    @PostMapping("/mask-all")
//    public ResponseEntity<String> maskAllSensitiveData(HttpServletRequest request) {
//        String username = getCurrentUsername(request);
//        User user = getUserByUsername(username);
//        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//        try {
//            maskingService.maskAllData();
//            return new ResponseEntity<>("所有数据已脱敏", HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    // 批量上传敏感数据
//    @PostMapping("/batch-upload")
//    public ResponseEntity<String> batchUploadSensitiveData(HttpServletRequest request, @RequestBody List<SensitiveData> sensitiveDataList) {
//        String username = getCurrentUsername(request);
//        User user = getUserByUsername(username);
//        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//        try {
//            for (SensitiveData data : sensitiveDataList) {
//                data.setUploaderId(user.getRoleId()); // 设置上传者ID为当前用户的角色ID
//            }
//            boolean result = maskingService.batchUpload(sensitiveDataList);
//            return new ResponseEntity<>(result ? "批量导入成功" : "批量导入失败", HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}