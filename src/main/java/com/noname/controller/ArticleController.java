package com.noname.controller;

import com.github.pagehelper.PageHelper;
import com.noname.entity.Article;
import com.noname.entity.Result;
import com.noname.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/article")
@RestController
public class ArticleController {



    @Autowired
    ArticleMapper articleMapper;

//    @Autowired
//    ArticleService articleService;


    @GetMapping("/{id}")
    public  Result<Article> getArticleById(@PathVariable("id") Integer id){

        Article article = articleMapper.selectByPrimaryKey(id);
        Result<Article> rs = new Result<>();
        rs.setData(article);
        return rs;
    }

    //读取文章列表
    @GetMapping("/list")
    public Result<List<Article>> getList(Integer pageNum, Integer pageSize){

        if(pageNum!=null && pageSize!=null)
           PageHelper.startPage(pageNum, pageSize);

        System.out.println(pageNum +" "+ pageSize);

        List<Article> ret = articleMapper.selectAll();

        Result<List<Article>> rs = new Result<>();
        rs.setData(ret);
        return rs;
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteArticle(@PathVariable("id")Integer id){
        articleMapper.deleteByPrimaryKey(id);
        return  new Result<>();
    }

    @PutMapping("/update")
    public  Result<String> updateArticle(Article article){

        if(articleMapper.updateByPrimaryKey(article) == 1){
            return new Result<>();
        }
        return new Result<>();
    }




}
