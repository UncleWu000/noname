package com.noname.controller;

import com.noname.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = "userlist")
    public Map<String, Object> getUsers(){
        System.out.println("userlist in");
        Map<String, Object> map = new HashMap<>();
        map.put("data", userMapper.selectAll());
        return map;
    }

    @GetMapping("/list2")
    @Cacheable(value = "userlist")
    public Map<String, Object> getUsers2(){
        System.out.println("userlist in");
        Map<String, Object> map = new HashMap<>();
        map.put("data", userMapper.selectAll());
        return map;
    }

    @RequestMapping("/cacheRemove")
    @CacheEvict(value = "userlist", allEntries = true)
    public void removeUserListCache(){
        System.out.println("删除了cache");
    }

}
