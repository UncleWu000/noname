package com.noname.entity;

import javax.persistence.Id;

public class CourseRoomPlan {
    @Id
    private Integer id;

    private Integer courseId;

    private Integer roomId;

    private Integer timeslotId;

    public Integer getTimeslotId() {
        return timeslotId;
    }

    public void setTimeslotId(Integer timeslotId) {
        this.timeslotId = timeslotId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }
}