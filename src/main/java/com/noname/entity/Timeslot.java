package com.noname.entity;

import javax.persistence.Id;

public class Timeslot {

    @Id
    private Integer id;
    private String timeSlot;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }
}
