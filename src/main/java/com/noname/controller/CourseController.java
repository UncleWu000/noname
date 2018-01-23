package com.noname.controller;

import com.noname.bo.Result;
import com.noname.entity.Course;
import com.noname.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/course")
@RestController
public class CourseController {

//    @PostConstruct
//    public void doAtFirst(){
//        initializing.afterPropertiesSet();
//    }
//
    @Autowired
    CourseService courseService;


    @GetMapping("/courseList")
    public List<Course> getCourseList(){

        List<Course> courses = new ArrayList<>();
        //courses = courseService.getCourseList();
        return courses;
    }

    @GetMapping("/test")
//    @Cacheable(value = "testNum")
    public String test(){
        courseService.getCourseNumber(1,10,1);
        return "success";
    }

    @PostMapping("/selecting")
    public Result selecting(Integer stuId, Integer courseId){
        if(courseService.stuSelectCourse(stuId, courseId)){
            return new Result();
        }
        return new Result(false);
    }


}
