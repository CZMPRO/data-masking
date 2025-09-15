package com.mypro.mapper;

import com.mypro.entity.Permission;
import com.mypro.entity.PermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PermissionMapper {
    long countByExample(PermissionExample example);

    int deleteByExample(PermissionExample example);

    int deleteByPrimaryKey(String permissionId);

    int insert(Permission row);

    int insertSelective(Permission row);

    List<Permission> selectByExample(PermissionExample example);

    Permission selectByPrimaryKey(String permissionId);

    int updateByExampleSelective(@Param("row") Permission row, @Param("example") PermissionExample example);

    int updateByExample(@Param("row") Permission row, @Param("example") PermissionExample example);

    int updateByPrimaryKeySelective(Permission row);

    int updateByPrimaryKey(Permission row);
}