package com.noname.mapper;

import com.noname.entity.Course;
import com.noname.util.BaseMapper;

import java.util.List;

public interface CourseMapper extends BaseMapper<Course> {

    List<Course> selectAll2();
}