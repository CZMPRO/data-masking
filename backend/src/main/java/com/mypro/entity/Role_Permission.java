package com.mypro.entity;

public class Role_Permission {
    private Integer id;

    private Integer roleId;

    private String permissionId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId == null ? null : permissionId.trim();
    }

    @Override
    public String toString() {
        return "Role_Permission{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", permissionId='" + permissionId + '\'' +
                '}';
    }
}