package com.noname.controller;

import com.github.pagehelper.PageHelper;
import com.noname.entity.Article;
import com.noname.entity.Result;
import com.noname.mapper.ArticleMapper;
import com.noname.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/article")
@RestController
public class ArticleController {



    @Autowired
    ArticleMapper articleMapper;

//    @Autowired
//    ArticleService articleService;

    @GetMapping("/list")
    public Result<List<Article>> getList(Integer pageNum, Integer pageSize){

        if(pageNum!=null && pageSize!=null)
           // PageHelper.startPage(pageNum, pageSize);

        System.out.println(pageNum +" "+ pageSize);

        List<Article> ret = articleMapper.selectAll();

        Result<List<Article>> rs = new Result<>();
        rs.setData(ret);
        return rs;
    }
}
