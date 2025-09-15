package com.mypro.service;

import com.mypro.entity.MaskingPolicy;
import com.mypro.entity.SensitiveData;
import com.mypro.entity.User;
import com.mypro.entity.Role;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface Masking_API {
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    List<SensitiveData> queryAllSensitiveDataForUser(User user) throws Exception;
}
