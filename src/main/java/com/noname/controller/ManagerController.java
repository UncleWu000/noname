package com.noname.controller;


import com.noname.entity.Admin;
import com.noname.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/manager")
@RestController
public class ManagerController {


    @Autowired
    AdminMapper adminMapper;

    @PostMapping("/add")
    public int addAdmin(Admin admin){
        return adminMapper.insertSelective(admin);
    }

    @DeleteMapping("/del/{id}")
    public int delAdmin(@PathVariable("id")Integer id){
        return adminMapper.deleteByPrimaryKey(id);
    }

    @PutMapping("/msgModify")
    public int pwChange(Admin admin){
        return adminMapper.updateByPrimaryKey(admin);
    }

    @GetMapping("/list")
    public List<Admin> getManagerList(){
        return adminMapper.selectAll();
    }

    @GetMapping("/get/{id}")
    public Admin getAdminById(@PathVariable("id")Integer id){
        return adminMapper.selectByPrimaryKey(id);
    }



}
