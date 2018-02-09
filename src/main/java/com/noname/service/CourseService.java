package com.noname.service;

import com.noname.entity.Course;

import java.util.List;

public interface CourseService {

    List<Course> sysGetCourseList();

    Integer getCourseNumber(Integer id, Integer numMax, Integer numNow);

    boolean stuSelectCourse(Integer stuId, Integer courseId);


}
