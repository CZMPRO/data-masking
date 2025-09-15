package com.mypro.service.impl;

import com.mypro.entity.Role_Permission;
import com.mypro.mapper.Role_PermissionMapper;
import com.mypro.service.Masking_API;

import com.mypro.entity.SensitiveData;
import com.mypro.entity.User;
import com.mypro.mapper.SensitiveDataMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MaskingServiceImpl implements Masking_API {

    @Autowired
    private SensitiveDataMapper sensitiveDataMapper;

    @Autowired
    private Role_PermissionMapper rolePermissionMapper;

    // 身份证号只显示第一位和最后一位
    private String maskIdCard(String idCard) {
        if (idCard == null || idCard.length() < 2) return idCard;
        return idCard.charAt(0) + "*".repeat(idCard.length() - 2) + idCard.charAt(idCard.length() - 1);
    }

    // 姓名只显示姓（如周某）
    private String maskName(String name) {
        if (name == null || name.length() < 1) return name;
        return name.charAt(0) + "某";
    }

    // 性别只显示第一个字
    private String maskGender(String gender) {
        if (gender == null || gender.length() < 1) return gender;
        return gender.substring(0, 1);
    }

    // 手机号只显示前三位和后四位
    private String maskPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() < 8) return phoneNumber;
        return phoneNumber.substring(0, 3) + "*".repeat(phoneNumber.length() - 7) + phoneNumber.substring(phoneNumber.length() - 4);
    }

    // 邮箱只显示@前面的部分
    private String maskEmail(String email) {
        if (email == null) return email;
        int atIndex = email.indexOf('@');
        if (atIndex == -1) return email;
        return email.substring(0, atIndex) + "*".repeat(email.length() - atIndex);
    }

    // 银行卡号只显示后四位
    private String maskBankCard(String bankCard) {
        if (bankCard == null || bankCard.length() < 4) return bankCard;
        return "*".repeat(bankCard.length() - 4) + bankCard.substring(bankCard.length() - 4);
    }

    // 上传时间只显示年月日
    private Date maskUploadTime(Date uploadTime) throws Exception {
        if (uploadTime == null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(uploadTime);
        return sdf.parse(formattedDate);
    }

    // 上传者ID只显示后两位
    private static Integer maskUploaderId(Integer uploaderId) {
        if (uploaderId == null) return null;
        String idStr = uploaderId.toString();
        if (idStr.length() <= 2) {
            return uploaderId; // 如果长度小于等于2，直接返回原值
        }
        String maskedPart = "0".repeat(idStr.length() - 2);
        String lastTwoDigits = idStr.substring(idStr.length() - 2);
        return Integer.parseInt(maskedPart + lastTwoDigits);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    @Override
    public List<SensitiveData> queryAllSensitiveDataForUser(User user) throws Exception {
        List<Role_Permission> permissions = getPermissionsForUser(user);
        Set<String>permission = permissions.stream().map(Role_Permission::getPermissionId).collect(Collectors.toSet());
        List<SensitiveData> dataList = sensitiveDataMapper.selectByExample(null);

        for (SensitiveData data : dataList) {
            applyMasking(data, permission);
        }
        return dataList;
    }

    private void applyMasking(SensitiveData data, Set<String> permissions) throws Exception {
        if (!permissions.contains("VIEW_ID_CARD")) {
            data.setIdNumber(maskIdCard(data.getIdNumber()));
        }
        if (!permissions.contains("VIEW_FULL_NAME")) {
            data.setName(maskName(data.getName()));
        }
        if (!permissions.contains("VIEW_GENDER")) {
            data.setGender(maskGender(data.getGender()));
        }
        if (!permissions.contains("VIEW_PHONE_NUMBER")) {
            data.setPhoneNumber(maskPhoneNumber(data.getPhoneNumber()));
        }
        if (!permissions.contains("VIEW_EMAIL")) {
            data.setEmail(maskEmail(data.getEmail()));
        }
        if (!permissions.contains("VIEW_BANK_CARD")) {
            data.setBankCard(maskBankCard(data.getBankCard()));
        }
        if (!permissions.contains("VIEW_UPLOAD_TIME")) {
            data.setUploadTime(maskUploadTime(data.getUploadTime()));
        }
        if (!permissions.contains("VIEW_UPLOADER_ID")) {
            data.setUploaderId(maskUploaderId(data.getUploaderId()));
        }
    }

    private List<Role_Permission> getPermissionsForUser(User user) {
        return rolePermissionMapper.selectPermissionsByRoleId(user.getRoleId());
    }

//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public SensitiveData addSensitiveData(SensitiveData data) throws Exception {
//        int result = sensitiveDataMapper.insert(data);
//        if (result != 1) {
//            throw new Exception("添加敏感数据失败");
//        } else {
//            return data;
//        }
//    }
//
//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public SensitiveData updateSensitiveData(SensitiveData data) throws Exception {
//        int result = sensitiveDataMapper.updateByPrimaryKey(data);
//        if (result != 1) {
//            throw new Exception("更新敏感数据失败");
//        } else {
//            return data;
//        }
//    }
//
//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public boolean deleteSensitiveData(Integer dataId) throws Exception {
//        int result = sensitiveDataMapper.deleteByPrimaryKey(dataId);
//        if (result != 1) {
//            throw new Exception("删除敏感数据失败");
//        } else {
//            return true;
//        }
//    }
}

