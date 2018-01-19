package com.noname.config;

import com.noname.entity.Course;
import com.noname.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyContextRefreshedEvent implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    CourseService courseService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(contextRefreshedEvent.getApplicationContext().getParent() == null){//root application context 没有parent，他就是老大.
            List<Course> courses = courseService.getCourseList();
            courses.forEach(s->{
                Integer id = s.getId();
                Integer num = s.getSelectedMax()-s.getSelectedNow();
                courseService.getCourseNumber(id, num);
                System.out.println("课程#"+id+"剩余可选"+" " + num);
            });
        }
    }


}
