package com.noname.mapper;

import com.noname.entity.Course;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface CourseMapper extends BaseMapper<Course> {

    List<Course> selectAll2();
}