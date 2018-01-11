package com.noname.entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "u_permission")
public class Permission {

    @Id
    private Long id;

    private String url;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}