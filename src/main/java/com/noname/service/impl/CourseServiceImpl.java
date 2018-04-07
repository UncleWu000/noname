package com.noname.service.impl;

import com.noname.entity.Course;
import com.noname.entity.Selected;
import com.noname.mapper.CourseMapper;
import com.noname.mapper.SelectedMapper;
import com.noname.service.CourseService;
import com.noname.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl extends BaseServiceImpl<CourseMapper, Course> implements CourseService {



    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    SelectedMapper selectedMapper;

    @Autowired
    CourseMapper courseMapper;

    @Override
    @Cacheable("courseList")
    public List<Course> sysGetCourseList() {

        List<Course> courses = dao.selectAll();
        System.out.println("get courseList");
        return courses;
    }


    @CachePut("courseNumber")
    public Map<String, Integer> sysGetSelectedNow(){
        List<Course> courses = dao.selectAll();
        Map<String, Integer> map = new HashMap<>();
        courses.forEach(s->{
            map.put(s.getCourseName(), s.getSelectedNow());
        });

        return map;
    }

    @Cacheable(value = "courseNumber")
    public Map<String, Integer> stuetSelectedNow(){
        List<Course> courses = dao.selectAll();
        Map<String, Integer> map = new HashMap<>();
        courses.forEach(s->{
            map.put(s.getCourseName(), s.getSelectedNow());
        });
        return map;
    }

    @CachePut(value = "courseNumber", key = "'course#'+#id")
    @CacheEvict(value = "courseNumber", key = "'course#'+#id", allEntries = true)
    public Integer getCourseNumber(Integer id, Integer numMax, Integer numNow){
        Integer rs = numMax-numNow;
        System.out.println("课程#"+id+"剩余可选"+" " + rs);
        return rs;
    }

    public  boolean stuSelectCourse(Integer stuId, Integer courseId){

        try {
            if(redisTemplate.opsForValue().increment("course#"+courseId, -1)>=0){
                Course c = courseMapper.selectByPrimaryKey(courseId);
                Selected s = new Selected();
                s.setStuId(stuId);
                List<Selected> selecteds = selectedMapper.select(s);
                if(selecteds!=null && selecteds.size()>2){
                    throw new Exception("选课不能超过2门");
                }
                s.setCourseName(c.getCourseName());
                s.setTeacher(c.getTeacher());
                s.setScore(c.getScore());
                selectedMapper.insert(s);
                System.out.println("学生"+stuId+"抢课成功!!");
                return true;
            }
        } catch (Exception e) {
            redisTemplate.opsForValue().increment("course#"+courseId, 1);
            e.printStackTrace();
        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean  stuSelectCourse2(Integer stuId, Integer courseId){
        synchronized (this){
            Course course = courseMapper.selectByPrimaryKey(1);
            course.setSelectedNow(course.getSelectedNow()+1);
            System.out.println(courseMapper.updateByPrimaryKey(course));
        }


        return true;
    }

    @Override
    public List<Course> selectAll2() {
        return dao.selectAll2();
    }

    @Override
    public boolean courseInport(MultipartFile file) throws IllegalAccessException {

        List<ArrayList<String>> excel = ExcelUtil.excelRead(file);

        Course course = new Course();
        for(ArrayList<String> row : excel){

            int index = 0;
            for(String cell : row){
                if(index == 0)
                    course.setCourseName(cell);
                else if(index == 1)
                    course.setScore(Integer.valueOf(cell));
                else if(index == 2)
                    course.setTeacher(cell);
                else if(index == 3)
                    course.setSelectedMax(Integer.valueOf(cell));
                else if(index == 4)
                    course.setSelectedNow(Integer.valueOf(cell));
                else if(index == 5)
                    course.setCourseType(cell);

                index ++;
            }

            dao.insertSelective(course);
        }

        return true;
    }


}
