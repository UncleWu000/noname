package com.noname.entity;

import javax.persistence.Table;

@Table(name = "u_user_role")
public class UserRole {
    private Long uid;

    private Long rid;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }
}