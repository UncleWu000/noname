package com.noname.controller;

import com.noname.bo.DataResult;
import com.noname.bo.Result;
import com.noname.entity.Selected;
import com.noname.service.SelectedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/selected")
public class SelectedController extends BaseController{

    @Autowired
    SelectedService selectedService;

    @GetMapping("test")
    public String text(){
        return "success";
    }

    @GetMapping("/list")
    public Result getStuSelected(){
        Integer stuId = 1;
        Selected s = new Selected();
        s.setStuId(stuId);
        List<Selected> selecteds = selectedService.select(s);
        return new DataResult<>(selecteds);
    }
}
