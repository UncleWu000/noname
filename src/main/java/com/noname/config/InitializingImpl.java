package com.noname.config;

import com.noname.service.CourseService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public class InitializingImpl implements InitializingBean {

    @Autowired
    CourseService courseService;

    @Override
    public void afterPropertiesSet() throws Exception {
        courseService.getCourseList();
    }
}
