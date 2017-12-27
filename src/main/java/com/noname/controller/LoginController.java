package com.noname.controller;

import com.noname.entity.Admin;
import com.noname.mapper.AdminMapper;
import com.noname.util.EncrypUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
public class LoginController {

    @Resource
    AdminMapper adminMapper;

    @RequestMapping("/login")
    public String login(Admin admin) throws Exception{

        List<Admin> adminList = adminMapper.select(admin);
        if(adminList.size()>0){
            admin = adminList.get(0);
            return EncrypUtils.encryp(admin.getUsername(), admin.getPassword());
        }else{
            return "账号或密码错误!";
        }
    }

}
