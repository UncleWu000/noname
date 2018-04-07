package com.noname.controller;

import com.noname.bo.DataResult;
import com.noname.bo.Result;
import com.noname.bo.user.CSSubject;
import com.noname.bo.user.CSToken;
import com.noname.constant.CSSubjectConst;
import com.noname.entity.Student;
import com.noname.entity.User;
import com.noname.mapper.StudentMapper;
import com.noname.mapper.UserMapper;
import com.noname.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@RestController
public class LoginController {

    @Autowired
    StudentMapper studentMapper;
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/myLogin")
    @ResponseBody
    public Map<String, Object> submitLogin(String username, String password, Model model){

        Map<String, Object> resultMap = new LinkedHashMap<>();
        try {

            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            System.out.println("i am here");

            System.out.println(token);
            SecurityUtils.getSubject().login(token);
            resultMap.put("status", "200");
            resultMap.put("message", "登陆成功!");
        }catch (Exception e){
            resultMap.put("status", "502");
            resultMap.put("message", e.getMessage());
        }

        return resultMap;
    }

    @RequestMapping("logOut")
    public void logOut(HttpServletRequest request){

        SecurityUtils.getSubject().logout();;
        String username = (String) SecurityUtils.getSubject().getSession().getId();
        System.out.println(username +" 已登出");
    }

    @RequestMapping("tokenLogin")
    public Result tokenLogin(String username, String password){
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return new Result(false);
        }
        Subject subject = SecurityUtils.getSubject();
        CSSubject csSubject = new CSSubject(1,username, 1, CSSubjectConst.ClientOrManage.MANAGE);
        UsernamePasswordToken token = new UsernamePasswordToken(csSubject.toJson(), password);
        token.setRememberMe(false);

        subject.login(token);
        CSToken csToken = generateAdminToken(csSubject);
        return new DataResult<>(csToken);
    }

    private CSToken generateAdminToken(CSSubject csSubject) {
        return csSubject.toToken();
    }


    @GetMapping("login")
    public Result login(String no, String pwd, Integer type){
        if(type == 1){
            Example example = new Example(Student.class);
            example.createCriteria().andEqualTo("no", no).andEqualTo("pwd", pwd);
            List<Student> students = studentMapper.selectByExample(example);
            if(students!=null && students.size()==1){
                System.out.println("["+no+"]登入系统");
                return new Result();
            }
        }else{
            Example example = new Example(User.class);
            example.createCriteria().andEqualTo("nickname", no).andEqualTo("pswd", pwd);
            List<User> users = userMapper.selectByExample(example);
            if(users!=null && users.size() == 1){
                System.out.println("管理员["+no+"]登入系统");
                return new Result();
            }
        }
        return new Result(false);
    }

}
