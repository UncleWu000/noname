package com.noname.vo;

public class PlanVO {
    private Integer id;
    private String courseName;
    private String roomName;
    private String roomId;
    private String timeslotId;
    private String timeslotStr;

    public String getTimeslotStr() {
        return timeslotStr;
    }

    public void setTimeslotStr(String timeslotStr) {
        this.timeslotStr = timeslotStr;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getTimeslotId() {
        return timeslotId;
    }

    public void setTimeslotId(String timeslotId) {
        this.timeslotId = timeslotId;
    }

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

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

}
