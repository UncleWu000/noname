package com.noname.controller;

import com.noname.entity.Admin;
import com.noname.mapper.AdminMapper;
import com.noname.util.EncrypUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class LoginController {

    @Resource
    AdminMapper adminMapper;

    @RequestMapping("/login")
    public Map<String, Object> login(Admin admin) throws Exception{

        List<Admin> adminList = adminMapper.select(admin);
        Map<String, Object> map = new HashMap<>();
        if(adminList.size()>0){
            admin = adminList.get(0);
            String token = EncrypUtils.encryp(admin.getUsername(), admin.getPassword());
            map.put("status",  true);
            map.put("token", token);
            return map;
        }else{
            map.put("status",  false);
            map.put("token", "null");
            return map;
        }
    }

}
