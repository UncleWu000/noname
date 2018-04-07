package com.noname.controller;

import com.noname.bo.Result;
import com.noname.entity.Course;
import com.noname.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<Object,Object> getCourseList(){
        Map<Object,Object> rs = new HashMap<>();
        List<Course> courses = new ArrayList<>();
        courses = courseService.sysGetCourseList();
        rs.put("code", 0);
        rs.put("msg", "");
        rs.put("count", courses.size());
        rs.put("data", courses);
        return rs;
    }

    @GetMapping("/test")
    public String test(){
        courseService.getCourseNumber(1,10,1);
        return "success";
    }

    @PostMapping("/selecting")
    public Result selecting(Integer stuId, Integer courseId){
        if(courseService.stuSelectCourse2(stuId, courseId)){
            return new Result();
        }
        return new Result(false);
    }


}
