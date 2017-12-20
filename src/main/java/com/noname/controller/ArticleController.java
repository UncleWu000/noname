package com.noname.controller;

import com.github.pagehelper.PageHelper;
import com.noname.entity.Article;
import com.noname.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RequestMapping("/article")
@RestController
public class ArticleController {



    @Autowired
    ArticleMapper articleMapper;

//    @Autowired
//    ArticleService articleService;


    //根据ID读取
    @GetMapping("/{id}")
    public  Article getArticleById(@PathVariable("id") Integer id){
        return articleMapper.selectByPrimaryKey(id);
    }

    //读取文章列表
    @GetMapping("/list")
    public List<Article> getList(Integer pageNum, Integer pageSize){

        if(pageNum!=null && pageSize!=null)
           PageHelper.startPage(pageNum, pageSize);

        System.out.println(pageNum +" "+ pageSize);

        List<Article> ret = articleMapper.selectAll();
        Collections.reverse(ret);
        return ret;
    }

    @DeleteMapping("/{id}")
    public int deleteArticle(@PathVariable("id")Integer id){
        return articleMapper.deleteByPrimaryKey(id);

    }

    @PutMapping("/update")
    public  int updateArticle(Article article){

        return articleMapper.updateByPrimaryKey(article);

    }




}
