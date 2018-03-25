package com.noname.entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "studentgroup")
public class StudentGroup {

    @Id
    private Integer id;
    private String groupName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
