package com.noname.event;

import com.noname.entity.Course;
import com.noname.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component
public class MyContextRefreshedEvent implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    CourseService courseService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(contextRefreshedEvent.getApplicationContext().getParent() == null){//root application context 没有parent，他就是老大.
            System.out.println(contextRefreshedEvent.getApplicationContext().getDisplayName()+"================================================");

            List<Course> courses = courseService.sysGetCourseList();
            courses.forEach(s->{
                System.out.println("!!!!!");
                Integer id = s.getId();
                Integer numMax = s.getSelectedMax();
                Integer numNow = s.getSelectedNow();
                courseService.getCourseNumber(id, numMax, numNow);
            });
        }
    }


}
