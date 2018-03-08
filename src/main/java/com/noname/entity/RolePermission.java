package com.noname.entity;


import javax.persistence.Table;

@Table(name = "u_role_permission")
public class RolePermission {
    private Long rid;

    private Long pid;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }
}