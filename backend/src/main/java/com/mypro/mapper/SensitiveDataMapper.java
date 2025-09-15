package com.mypro.mapper;

import com.mypro.entity.SensitiveData;
import com.mypro.entity.SensitiveDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SensitiveDataMapper {
    long countByExample(SensitiveDataExample example);

    int deleteByExample(SensitiveDataExample example);

    int deleteByPrimaryKey(Integer dataId);

    int insert(SensitiveData row);

    int insertSelective(SensitiveData row);

    List<SensitiveData> selectByExample(SensitiveDataExample example);

    SensitiveData selectByPrimaryKey(Integer dataId);

    int updateByExampleSelective(@Param("row") SensitiveData row, @Param("example") SensitiveDataExample example);

    int updateByExample(@Param("row") SensitiveData row, @Param("example") SensitiveDataExample example);

    int updateByPrimaryKeySelective(SensitiveData row);

    int updateByPrimaryKey(SensitiveData row);
}