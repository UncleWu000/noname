package com.noname.config;

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

            List<Course> courses = courseService.getCourseList();
            System.out.println(Arrays.toString(new List[]{courses}));
            courses.forEach(s->{
                System.out.println("!!!!!");
                Integer id = s.getId();
                Integer numMax = s.getSelectedMax();
                Integer numNow = s.getSelectedNow();
                Integer rs = courseService.getCourseNumber(id, numMax, numNow);
                System.out.println("课程#"+id+"剩余可选"+" " + rs);
            });
        }
    }


}
