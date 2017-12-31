package com.noname.controller;

import com.github.pagehelper.Page;
import com.noname.annotation.Pagination;
import com.noname.entity.Article;
import com.noname.mapper.ArticleMapper;
import com.noname.vo.ArticleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        System.out.println(id + "========================");
        return articleMapper.selectByPrimaryKey(id);
    }

//    //读取文章列表
//    @GetMapping("/list")
//    public List<Article> getList(Integer pageNum, Integer pageSize){
//
//        if(pageNum ==null || pageSize==null){
//            pageNum = 1;
//            pageSize = 10;
//        }
//        PageHelper.startPage(pageNum, pageSize);
//
//        System.out.println(pageNum +" "+ pageSize);
//
//        List<Article> ret = articleMapper.selectAll();
//        Collections.reverse(ret);
//        return ret;
//    }

    //读取文章列表
    @GetMapping("/list/{rule}")
    @Pagination
    public Map<String, Object> getList2(@PathVariable(required = false, value = "rule")String rule){
        System.out.println("获取文章列表...");
        Map<String, Object> map = new HashMap<>();

        if(rule.equals("likeasc")){
            rule = "ORDER BY like_count ASC";

        }else if(rule.equals("likedesc")){
            rule = "ORDER BY like_count DESC";

        }else if(rule.equals("viewasc")){
            rule = "ORDER BY view_count ASC";

        }else if(rule.equals("viewdesc")){
            rule = "ORDER BY view_count DESC";

        }else if(rule.equals("timeasc")){
            rule = "ORDER BY create_date ASC";

        }else{
            rule = "ORDER BY create_date DESC";
        }

//        List<Article> ret = articleMapper.selectAllByRule(rule);
        List<Article> ret = articleMapper.selectAll();

        Page<Article> page = (Page)ret;
        map.put("pageNum", page.getPageNum());
        map.put("pageSize", page.getPageSize());
        map.put("total", page.getTotal());
        List<ArticleVO> avos = new ArrayList<>();

        for(Article item : ret){
            ArticleVO avo = new ArticleVO();
            BeanUtils.copyProperties(item, avo);
            avos.add(avo);
        }

        map.put("data", avos);
        return map;
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
