package com.mypro.mapper;

import com.mypro.entity.AuditLog;
import com.mypro.entity.AuditLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuditLogMapper {
    long countByExample(AuditLogExample example);

    int deleteByExample(AuditLogExample example);

    int deleteByPrimaryKey(Integer auditId);

    int insert(AuditLog row);

    int insertSelective(AuditLog row);

    List<AuditLog> selectByExample(AuditLogExample example);

    AuditLog selectByPrimaryKey(Integer auditId);

    int updateByExampleSelective(@Param("row") AuditLog row, @Param("example") AuditLogExample example);

    int updateByExample(@Param("row") AuditLog row, @Param("example") AuditLogExample example);

    int updateByPrimaryKeySelective(AuditLog row);

    int updateByPrimaryKey(AuditLog row);
}