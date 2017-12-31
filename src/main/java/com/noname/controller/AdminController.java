package com.noname.controller;

import com.noname.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("user")
@RestController
public class AdminController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/list")
    public Map<String, Object> getUsers(){
        Map<String, Object> map = new HashMap<>();
        map.put("data", userMapper.selectAll());
        return map;
    }
}
