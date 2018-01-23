package com.noname.service.impl;

import com.noname.entity.Course;
import com.noname.mapper.CourseMapper;
import com.noname.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    @CachePut("courseList")
    public List<Course> sysGetCourseList() {

        List<Course> courses = courseMapper.selectAll();
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
        System.out.println("课程#"+id+"剩余可选"+" " + rs);
        return rs;
    }

    public  boolean stuSelectCourse(Integer stuId, Integer courseId){

        try {
            if(redisTemplate.opsForValue().increment("course#"+courseId, -1)>=0){
                System.out.println("学生"+stuId+"抢课成功!!");
                throw new Exception();
//                return true;
            }
        } catch (Exception e) {
            redisTemplate.opsForValue().increment("course#"+courseId, 1);
            e.printStackTrace();
        }
        return false;
    }



}
