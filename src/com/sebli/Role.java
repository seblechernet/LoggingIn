package com.sebli;

import java.util.ArrayList;
import java.util.List;

public class Role {
    private long roleId;
    private String roleName;
    private List<User> users;


    public Role(long roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
        users=new ArrayList<>();

    }

    public Role() {
        users=new ArrayList<>();

    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
