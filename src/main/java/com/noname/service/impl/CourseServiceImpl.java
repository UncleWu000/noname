package com.noname.service.impl;

import com.noname.entity.Course;
import com.noname.mapper.CourseMapper;
import com.noname.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseMapper courseMapper;

    @Override
    @Cacheable("courseList")
    public List<Course> getCourseList() {

        List<Course> courses = courseMapper.selectAll2();
        return courses;
    }


    @CachePut("courseNumber")
    public Map<String, Integer> sysGetSelectedNow(){
        List<Course> courses = courseMapper.selectAll();
        Map<String, Integer> map = new HashMap<>();
        courses.forEach(s->{
            map.put(s.getCourseName(), s.getSelectedNow());
        });

        return map;
    }

    @Cacheable("courseNumber")
    public Map<String, Integer> stuetSelectedNow(){
        List<Course> courses = courseMapper.selectAll();
        Map<String, Integer> map = new HashMap<>();
        courses.forEach(s->{
            map.put(s.getCourseName(), s.getSelectedNow());
        });
        return map;
    }

    @CachePut(value = "courseNumber", key = "'course#'+#id")
    public Integer getCourseNumber(Integer id, Integer numMax, Integer numNow){
        Integer rs = numMax-numNow;
        return rs;
    }



}
