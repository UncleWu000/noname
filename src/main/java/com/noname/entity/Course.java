package com.noname.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Id;

public class Course {

    @Id
    private Integer id;

    private String courseName;

    private Integer score;

    private String teacher;

    private Integer selectedMax;

    private Integer selectedNow;

    private String courseType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Integer getSelectedMax() {
        return selectedMax;
    }

    public void setSelectedMax(Integer selectedMax) {
        this.selectedMax = selectedMax;
    }

    public Integer getSelectedNow() {
        return selectedNow;
    }

    public void setSelectedNow(Integer selectedNow) {
        this.selectedNow = selectedNow;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }
}