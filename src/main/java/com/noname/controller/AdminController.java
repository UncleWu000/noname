package com.noname.controller;

import com.noname.bo.DataResult;
import com.noname.bo.Result;
import com.noname.entity.*;
import com.noname.exception.ServiceException;
import com.noname.mapper.BuildingMapper;
import com.noname.mapper.ClassroomMapper;
import com.noname.mapper.CourseRoomPlanMapper;
import com.noname.mapper.TimeslotMapper;
import com.noname.service.*;
import com.noname.util.DateUtils;
import com.noname.util.ExcelUtil;
import com.noname.util.PoiExcelExport;
import com.noname.vo.CourseStuSelectedVO;
import com.noname.vo.PlanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
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

    @Autowired
    BuildingMapper buildingMapper;

    @Autowired
    CourseRoomPlanMapper courseRoomPlanMapper;

    @Autowired
    ClassroomMapper classroomMapper;

    @Autowired
    TimeslotMapper timeslotMapper;

    @Autowired
    SelectedService selectedService;


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
    public Result getCourseList(Course course){
        DataResult<List<Course>> rs = new DataResult<>();

        try {
            List<Course> courseList = courseService.select(course);
            rs.setData(courseList);
        } catch (Exception e) {
            rs.setExceptionStatus(e.getMessage());
            e.printStackTrace();
        }

        return rs;
    }

    @GetMapping("stuSelected")
    public Result getStuSelected(String courseName){

    return new DataResult<>(selectedService.getCourseStuSelected(courseName));
    }

    @PostMapping("/course")
    public Result createOrUpdateCourse(Course course){
        Result rs = new Result();
        try {
            if(course.getId()!=null){
                System.out.println(courseService.updateByPrimaryKeySelective(course)+"--update");
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
                System.out.println(classroomService.updateByPrimaryKeySelective(classroom)+"--update");
            }else{
                System.out.println(classroomService.insertSelective(classroom)+"--insert");
            }
        } catch (Exception e) {
            rs.setExceptionStatus(e.getMessage());
            e.printStackTrace();
        }
        return rs;
    }

    @DeleteMapping("classroom")
    public Result deleteClassroom(@RequestParam(value = "ids[]") Integer[] ids){
        DataResult rs = new DataResult<>();
        String ans = "";
        for(Integer id : ids){
            if(!(classroomService.deleteByPrimaryKey(id)==0)){
                ans +=","+id;
            }
        }
        if(ans.equals("")){
            rs.setData("刪除成功");
        }else{
            rs.setData("ID["+ans.substring(1)+"]刪除失敗");
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

        if(courseRoomPlan.getTimeslotId() == 0){
            Integer roomId = courseRoomPlan.getRoomId();
            courseRoomPlan.setRoomId(null);
            courseRoomPlan.setTimeslotId(null);
            List<CourseRoomPlan> crps = courseRoomPlanService.select(courseRoomPlan);
            if(crps!=null && crps.size()>0){
                courseRoomPlan = crps.get(0);
                courseRoomPlan.setRoomId(roomId);
                courseRoomPlan.setTimeslotId(0);
                courseRoomPlanService.updateByPrimaryKeySelective(courseRoomPlan);
            }else{
                courseRoomPlan.setRoomId(roomId);
                courseRoomPlan.setTimeslotId(0);
                courseRoomPlanService.insertSelective(courseRoomPlan);
            }
        }else{
            Integer roomId = courseRoomPlan.getRoomId();
            Integer timeslotId = courseRoomPlan.getTimeslotId();
            Integer courseId = courseRoomPlan.getCourseId();
            courseRoomPlan.setCourseId(null);
            List<CourseRoomPlan> crps = courseRoomPlanService.select(courseRoomPlan);
            if(crps!=null && crps.size()>0){
                return new DataResult<>("该时间段已被占用");
            }else{
                courseRoomPlan.setCourseId(courseId);
                courseRoomPlan.setRoomId(null);
                courseRoomPlan.setTimeslotId(null);
                try {

                    courseRoomPlan = courseRoomPlanService.select(courseRoomPlan).get(0);
                    courseRoomPlan.setRoomId(roomId);
                    courseRoomPlan.setTimeslotId(timeslotId);
                    courseRoomPlanService.updateByPrimaryKeySelective(courseRoomPlan);
                } catch (Exception e) {
                    courseRoomPlan.setRoomId(roomId);
                    courseRoomPlan.setTimeslotId(timeslotId);
                    courseRoomPlanService.insertSelective(courseRoomPlan);
                }
            }


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
        System.out.println(student.getNo()+student.getClas()+student.getName());
        List<Student> students = studentService.select(student);

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

    @GetMapping("/building")
    public Result getBuilding(){

        return new DataResult<>(buildingMapper.selectAll());
    }

    @GetMapping("/timeslot")
    public  Result getTimeslot(Integer roomId){

        return new DataResult<>(timeslotMapper.getUsefulTs(roomId));
    }

    @PostMapping("/studentInport")
    public Result studentInport(MultipartFile file){
        Result rs = new Result();

        try {
            studentService.studentInport(file);
        } catch (Exception e) {

            e.printStackTrace();
            rs.setExceptionStatus();
            rs.setMsg(e.getMessage());
        }
        return rs;
    }


    @GetMapping("/studentOutput")
    public void studentOutput(@RequestParam(value = "ids") List<Integer> ids,HttpServletResponse response){


        /**
         * excel对应字段
         */
         String[] TITLE_COLUMN = new String[]{"id","no","pwd","sex","name","clas"};
        /**
         * excel对应名称
         */
         String[] TITLE_NAME = new String[]{"ID","学号","密码","性别","名字","班别"};

        /**
         * excel文件名
         */
         String FILE_NAME = "学生账号信息";

        /**
         * excel名称
         */
         String SHEET_NAME = "sheet1";

         int SIZE = 13;

        /**
         * excel大小
         */
         int[] TITLE_SIZE = new int[]{SIZE, SIZE, SIZE, SIZE, SIZE, SIZE};

         Result rs = new Result();

        try {
            Map<Integer, Boolean> map = new HashMap<>();
            for(Integer id : ids){
                map.put(id, true);
            }
            List<Student> students = studentService.selectAll();
            students = students.stream().filter(s->{
                if(map.get(s.getId()) != null){
                    return true;
                }
                return false;
            }).collect(Collectors.toList());
            PoiExcelExport  pee = new PoiExcelExport(response, FILE_NAME+DateUtils.format(new Date(), DateUtils.YMDHMS),SHEET_NAME);
            String[] colFormula = new String[TITLE_COLUMN.length];
            pee.setColFormula(colFormula);
            pee.writeExcel(TITLE_COLUMN, TITLE_NAME, TITLE_SIZE, students);
        } catch (Exception e) {
            e.printStackTrace();
            rs.setMsg(e.getMessage());
        }

    }

    @PostMapping("courseInport")
    public Result courseInport(MultipartFile file) throws IllegalAccessException {

        Result rs = new Result();


        try {
            courseService.courseInport(file);
        } catch (IllegalAccessException e) {
            rs.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return rs;
    }


    @GetMapping("/courseOutput")
    public void courseOutput(@RequestParam(value = "ids") List<Integer> ids,HttpServletResponse response){


        /**
         * excel对应字段
         */
        String[] TITLE_COLUMN = new String[]{"id","courseName","score","teacher","selectedMax","selectedNow","courseType"};
        /**
         * excel对应名称
         */
        String[] TITLE_NAME = new String[]{"ID","课程名称","学分","任课老师","限选","已选","课程分类"};

        /**
         * excel文件名
         */
        String FILE_NAME = "课程数据";

        /**
         * excel名称
         */
        String SHEET_NAME = "sheet1";

        int SIZE = 13;

        /**
         * excel大小
         */
        int[] TITLE_SIZE = new int[]{SIZE, SIZE, SIZE, SIZE, SIZE, SIZE,SIZE};

        Result rs = new Result();

        try {
            Map<Integer, Boolean> map = new HashMap<>();
            for(Integer id : ids){
                map.put(id, true);
                System.out.println(map.get(id));
            }
            List<Course> courses = courseService.selectAll();
            courses.forEach(s->{
                System.out.println(s.getCourseName());
            });
            courses = courses.stream().filter(s->{
                if(map.get(s.getId()) != null){
                    return true;
                }
                return false;
            }).collect(Collectors.toList());

            System.out.println("====================");
            courses.forEach(s->{
                System.out.println(s.getCourseName());
            });
            PoiExcelExport  pee = new PoiExcelExport(response, FILE_NAME+DateUtils.format(new Date(), DateUtils.YMDHMS),SHEET_NAME);
            String[] colFormula = new String[TITLE_COLUMN.length];
            pee.setColFormula(colFormula);
            pee.writeExcel(TITLE_COLUMN, TITLE_NAME, TITLE_SIZE, courses);
        } catch (Exception e) {
            e.printStackTrace();
            rs.setMsg(e.getMessage());
        }

    }

    @GetMapping("/selectOutput")
    public Result selectOutput(HttpServletResponse response, @RequestParam(value = "ids") List<Integer> ids, String courseName){


        /**
         * excel对应字段
         */
        String[] TITLE_COLUMN = new String[]{"id","no","stuName","sex","clas"};
        /**
         * excel对应名称
         */
        String[] TITLE_NAME = new String[]{"ID","学号","姓名","性别","班别"};

        /**
         * excel名称
         */
        String SHEET_NAME = "sheet1";

        int SIZE = 13;

        /**
         * excel大小
         */
        int[] TITLE_SIZE = new int[]{SIZE, SIZE, SIZE, SIZE, SIZE};


        List<CourseStuSelectedVO> cssv = selectedService.getCourseStuSelected(courseName);
        Map<Integer, Boolean> map = new HashMap<>();
        for(Integer id : ids){
            map.put(id, true);
        }

        cssv = cssv.stream().filter(s->{
            if(map.get(s.getId())!=null){
                return true;
            }
            return false;
        }).collect(Collectors.toList());

        PoiExcelExport  pee = new PoiExcelExport(response, "《"+courseName+"》选课情况"+DateUtils.format(new Date(), DateUtils.YMDHMS),SHEET_NAME);
        String[] colFormula = new String[TITLE_COLUMN.length];
        pee.setColFormula(colFormula);
        pee.writeExcel(TITLE_COLUMN, TITLE_NAME, TITLE_SIZE, cssv);

        return new Result();

    }
}
