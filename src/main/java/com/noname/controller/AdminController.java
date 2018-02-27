package com.noname.controller;

import com.noname.bo.DataResult;
import com.noname.bo.Result;
import com.noname.entity.Article;
import com.noname.entity.User;
import com.noname.mapper.UserMapper;
import com.noname.service.ArticleService;
import com.noname.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/admin")
@RestController
public class AdminController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    ArticleService articleService;

    @GetMapping("/userList")
    public Result getUserList(Integer id){
        DataResult<List<User>> rs = new DataResult<>();

        try {
            List<User> userList = userMapper.selectAll();
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
                userMapper.updateByPrimaryKeySelective(user);
            }else{
                userMapper.insertSelective(user);
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
            if(userMapper.deleteByPrimaryKey(id)==0){
                fail += fail.equals("")?"":", ";
                fail += id;
            };
        }
        System.out.println(fail);
        return new Result();
    }

    @PostMapping("/userImport")
    public Result importUser(MultipartFile file){
        List<ArrayList<String>> userExcel = ExcelUtil.excelRead(file);

        for(ArrayList<String> row : userExcel){

            for(String cell : row){

            }
        }
        return null;
    }


    @GetMapping("/articleList")
    public Result getArticleList(Integer id){
        DataResult<List<Article>> rs = new DataResult<>();

        try {
            List<Article> articleList = articleService.selectAll();
            if(id!=null){
                articleList = articleList.stream().filter(s->s.getId().equals(id)).collect(Collectors.toList());
            }
            rs.setData(articleList);
        } catch (Exception e) {
            rs.setExceptionStatus(e.getMessage());
            e.printStackTrace();
        }

        return rs;
    }

    @PostMapping("/article")
    public Result createOrUpdateArticle(Article article){
        Result rs = new Result();
        try {
            if(article.getId()!=null){
                articleService.updateByPrimaryKeySelective(article);
            }else{
                articleService.insertSelective(article);
            }
        } catch (Exception e) {
            rs.setExceptionStatus(e.getMessage());
            e.printStackTrace();
        }
        return rs;
    }

    @DeleteMapping("/article")
    public Result deleteArticle(Integer[] ids){
        Result rs = new Result();

        String fail = "";
        for(Integer id : ids){
            if(articleService.deleteByPrimaryKey(id) == 0){
                fail += fail.equals("")?"":", ";
                fail += id;
            }
        }
        System.out.println("fail : " +fail);
        return rs;
    }

    @PostMapping("/articleImport")
    public Result importArticle(MultipartFile file){
        List<ArrayList<String>> userExcel = ExcelUtil.excelRead(file);

        for(ArrayList<String> row : userExcel){

            for(String cell : row){

            }
        }
        return null;
    }



}
