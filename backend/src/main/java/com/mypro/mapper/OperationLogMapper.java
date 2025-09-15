package com.mypro.mapper;

import com.mypro.entity.OperationLog;
import com.mypro.entity.OperationLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OperationLogMapper {
    long countByExample(OperationLogExample example);

    int deleteByExample(OperationLogExample example);

    int deleteByPrimaryKey(Integer logId);

    int insert(OperationLog row);

    int insertSelective(OperationLog row);

    List<OperationLog> selectByExample(OperationLogExample example);

    OperationLog selectByPrimaryKey(Integer logId);

    int updateByExampleSelective(@Param("row") OperationLog row, @Param("example") OperationLogExample example);

    int updateByExample(@Param("row") OperationLog row, @Param("example") OperationLogExample example);

    int updateByPrimaryKeySelective(OperationLog row);

    int updateByPrimaryKey(OperationLog row);
}