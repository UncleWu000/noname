package com.noname.controller;

import com.noname.bo.DataResult;
import com.noname.bo.Result;
import com.noname.entity.Classroom;
import com.noname.entity.Course;
import com.noname.entity.User;
import com.noname.service.ClassroomService;
import com.noname.service.CourseService;
import com.noname.service.UserService;
import com.noname.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/admin")
@RestController
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    CourseService courseService;

    @Autowired
    ClassroomService classroomService;

    @GetMapping("/userList")
    public Result getUserList(Integer id){
        DataResult<List<User>> rs = new DataResult<>();

        try {
            List<User> userList = userService.selectAll();
            if(id!=null){
                userList = userList.stream().filter(s->s.getId().equals(id)).collect(Collectors.toList());
            }
            rs.setData(userList);
        } catch (Exception e) {
            rs.setExceptionStatus(e.getMessage());
            e.printStackTrace();
        }

        return rs;
    }

    @PostMapping("/user")
    public Result createOrUpdateUser(User user){
        Result rs = new Result();

        try {
            if(user.getId()!=null){
                userService.updateByPrimaryKeySelective(user);
            }else{
                userService.insertSelective(user);
            }
        } catch (Exception e) {
            rs.setExceptionStatus(e.getMessage());
            e.printStackTrace();
        }

        return rs;
    }


    @DeleteMapping("/user")
    public Result delUser(Integer[] ids){

        String fail = "";

        for(Integer id : ids){
            if(userService.deleteByPrimaryKey(id)==0){
                fail += fail.equals("")?"":", ";
                fail += id;
            };
        }
        System.out.println(fail);
        return new Result();
    }

    @PostMapping("/userImport")
    public Result importUser(MultipartFile file){
        DataResult<String> rs = new DataResult<>();
        List<ArrayList<String>> userExcel = ExcelUtil.excelRead(file);
        String res = userService.userImport(userExcel);
        rs.setData(res);
        return rs;
    }


    @GetMapping("/courseList")
    public Result getCourseList(Integer id){
        DataResult<List<Course>> rs = new DataResult<>();

        try {
            List<Course> courseList = courseService.selectAll();
            if(id!=null){
                courseList = courseList.stream().filter(s->s.getId().equals(id)).collect(Collectors.toList());
            }
            rs.setData(courseList);
        } catch (Exception e) {
            rs.setExceptionStatus(e.getMessage());
            e.printStackTrace();
        }

        return rs;
    }

    @PostMapping("/course")
    public Result createOrUpdateCourse(Course course){
        Result rs = new Result();
        try {
            if(course.getId()!=null){
                courseService.updateByPrimaryKeySelective(course);
            }else{
                courseService.insertSelective(course);
            }
        } catch (Exception e) {
            rs.setExceptionStatus(e.getMessage());
            e.printStackTrace();
        }
        return rs;
    }

    @DeleteMapping("/course")
    public Result deleteCourse(Integer[] ids){
        Result rs = new Result();

        String fail = "";
        for(Integer id : ids){
            if(courseService.deleteByPrimaryKey(id) == 0){
                fail += fail.equals("")?"":", ";
                fail += id;
            }
        }
        System.out.println("fail : " +fail);
        return rs;
    }

    @PostMapping("/courseImport")
    public Result importCourse(MultipartFile file){
        List<ArrayList<String>> userExcel = ExcelUtil.excelRead(file);

        for(ArrayList<String> row : userExcel){

            for(String cell : row){

            }
        }
        return null;
    }

    @GetMapping("/classroom")
    public Result getClassroomList(Integer id){
        DataResult<List<Classroom>> rs = new DataResult<>();

        try {
            List<Classroom> classroomList = classroomService.selectAll();
            if(id!=null){
                classroomList = classroomList.stream().filter(s->s.getId().equals(id)).collect(Collectors.toList());
            }
            rs.setData(classroomList);
        } catch (Exception e) {
            rs.setExceptionStatus(e.getMessage());
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/classroom")
    public Result createOrUpdateClassroom(Classroom classroom){
        Result rs = new Result();

        try {
            if(classroom.getId()!=null){
                classroomService.updateByPrimaryKeySelective(classroom);
            }else{
                classroomService.insertSelective(classroom);
            }
        } catch (Exception e) {
            rs.setExceptionStatus(e.getMessage());
            e.printStackTrace();
        }
        return rs;
    }

//    public Result deleteClassroom(Integer id){
//        Result rs = new Result();
//
//    }


}
