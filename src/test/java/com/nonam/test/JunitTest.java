package com.nonam.test;

import com.noname.Application;
import com.noname.entity.Article;
import com.noname.mapper.ArticleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class) //junit 提供, springRunner.classspring
@SpringBootTest(classes = Application.class) //springboot 项目传入启动类class
//@ContextConfiguration(classes = Application.class) //spring 项目用这个注解传入配置文件, 可以是数组
public class JunitTest {

    @Autowired
    ArticleMapper articleMapper;
    @Test
    public void test1(){
        List<Article> articles = articleMapper.selectAll();

        articles.forEach(s-> System.out.println(s.toString()));
        System.out.println("lallala");
    }
}
