package com.noname.service;

import com.noname.entity.Course;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService extends BaseService<Course>{

    List<Course> sysGetCourseList();

    Integer getCourseNumber(Integer id, Integer numMax, Integer numNow);

    boolean stuSelectCourse(Integer stuId, Integer courseId);

    List<Course> selectAll2();

    boolean courseInport(MultipartFile file) throws IllegalAccessException;


}
