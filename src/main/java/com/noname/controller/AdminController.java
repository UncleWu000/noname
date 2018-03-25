package com.noname.controller;

import com.noname.bo.DataResult;
import com.noname.bo.Result;
import com.noname.entity.*;
import com.noname.exception.ServiceException;
import com.noname.service.*;
import com.noname.util.ExcelUtil;
import com.noname.vo.PlanVO;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.Service;
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

    @Autowired
    CourseRoomPlanService courseRoomPlanService;

    @Autowired
    StudentService studentService;


    @Autowired
    GroupService groupService;

    @Autowired
    ClassgroupService classgroupService;


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
            List<Course> courseList = courseService.selectAll2();
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
    public Result deleteCourse(@RequestParam(value = "ids[]") Integer[] ids){
        DataResult rs = new DataResult<>();

        String fail = "";
        for(Integer id : ids){
            if(courseService.deleteByPrimaryKey(id) == 0){
                fail += fail.equals("")?"":", ";
                fail += id;
            }
        }
        System.out.println("ID[" +fail+"]删除失败");
        rs.setData(fail.equals("")?"删除成功":fail);
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
    public Result getClassroomList(Integer id, @RequestParam(defaultValue = "false") boolean used){
        DataResult<List<Classroom>> rs = new DataResult<>();

        try {
            List<Classroom> classroomList = classroomService.selectAll();
            if(id!=null){
                classroomList = classroomList.stream().filter(s->s.getId().equals(id)).collect(Collectors.toList());
            }
            if(used){
               classroomList = classroomList.stream().filter(s->s.getUsed().equals(0)).collect(Collectors.toList());
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

    @DeleteMapping("classroom")
    public Result deleteClassroom(Integer id){
        Result rs = new Result();

        if(!(classroomService.deleteByPrimaryKey(id)==0)){
            rs.setMsg("删除失败，不存在该记录");

        }
        return rs;
    }

    @GetMapping("/crplan")
    public Result getPlan(){
        DataResult<List<PlanVO>> rs = new DataResult<>();

        List<PlanVO> courseRoomPlans = courseRoomPlanService.getPlanVo();
        rs.setData(courseRoomPlans);
        return rs;
    }

    @PostMapping("/crplan")
    public Result createOrUpdate(CourseRoomPlan courseRoomPlan) throws ServiceException {

        Integer roomId = courseRoomPlan.getRoomId();
        Classroom courseRoomPlan1 = classroomService.selectByPrimaryKey(roomId);
        if(courseRoomPlan1!=null && courseRoomPlan1.getUsed()==1){
            throw new ServiceException("该教室已被占用");
        }

        if(courseRoomPlan.getId()==null){
            courseRoomPlanService.insertSelective(courseRoomPlan);
        }else{
            courseRoomPlanService.updateByPrimaryKeySelective(courseRoomPlan);
        }
        return new Result();
    }

    @DeleteMapping("/crplan")
    public Result deletePlan(Integer id){
        if(courseRoomPlanService.deleteByPrimaryKey(id) == 0){
            return new Result("不存在相应记录");
        }else {
            return new Result();
        }
    }


    @GetMapping("/student")
    public Result getStudentList(Student student){

        List<Student> students = studentService.selectAll();

        return new DataResult<>(students);
    }

    @PostMapping("/student")
    public Result createOrUpdataStu(Student student){

        try {
            if(student.getId() == null){
                studentService.insertSelective(student);
            }else{
                studentService.updateByPrimaryKeySelective(student);
            }
        } catch (Exception e) {
            return new Result(e.getMessage());
        }
        return new Result();
    }


    @DeleteMapping("/student")
    public Result delStudent(@RequestParam(value = "ids[]")  Integer[]  ids){
        String rs ="";
        System.out.println(ids+"==================");
        for(Integer id : ids){
            if (studentService.deleteByPrimaryKey(id)==0) {
                rs+=","+id;
            }
        }
        if (rs.equals("")){
            return new DataResult<>("删除成功");
        }else{
            return new DataResult<>("ID:["+rs.substring(1)+"]删除失败");
        }
    }


    @GetMapping("group")
    public Result getGroup(){
        return new DataResult<>(groupService.selectAll());
    }

    @GetMapping("coursegroup")
    public Result getClassgroup(){

        return new DataResult<>(classgroupService.selectAll());
    }

}
