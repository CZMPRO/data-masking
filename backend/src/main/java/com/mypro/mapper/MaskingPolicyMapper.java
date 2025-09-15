package com.mypro.mapper;

import com.mypro.entity.MaskingPolicy;
import com.mypro.entity.MaskingPolicyExample;
import java.util.List;

import com.mypro.entity.User;
import org.apache.ibatis.annotations.Param;

public interface MaskingPolicyMapper {
    long countByExample(MaskingPolicyExample example);

    int deleteByExample(MaskingPolicyExample example);

    int deleteByPrimaryKey(Integer policyId);

    int insert(MaskingPolicy row);

    int insertSelective(MaskingPolicy row);

    List<MaskingPolicy> selectByExample(MaskingPolicyExample example);

    MaskingPolicy selectByPrimaryKey(Integer policyId);

    int updateByExampleSelective(@Param("row") MaskingPolicy row, @Param("example") MaskingPolicyExample example);

    int updateByExample(@Param("row") MaskingPolicy row, @Param("example") MaskingPolicyExample example);

    int updateByPrimaryKeySelective(MaskingPolicy row);

    int updateByPrimaryKey(MaskingPolicy row);

}