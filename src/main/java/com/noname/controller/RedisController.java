package com.noname.controller;


import com.noname.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/redis")
@RestController
public class RedisController {

    @Autowired
    RedisService redisService;

    @GetMapping("/test")
    public String test(){
        return redisService.getRdeisInfo();
    }
}
