package com.mypro.mapper;

import com.mypro.entity.Role_Permission;
import com.mypro.entity.Role_PermissionExample;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface Role_PermissionMapper {
    long countByExample(Role_PermissionExample example);

    int deleteByExample(Role_PermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role_Permission row);

    int insertSelective(Role_Permission row);

    List<Role_Permission> selectByExample(Role_PermissionExample example);

    Role_Permission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") Role_Permission row, @Param("example") Role_PermissionExample example);

    int updateByExample(@Param("row") Role_Permission row, @Param("example") Role_PermissionExample example);

    int updateByPrimaryKeySelective(Role_Permission row);

    int updateByPrimaryKey(Role_Permission row);

    List<Role_Permission> selectPermissionsByRoleId(Integer rold_id);
}