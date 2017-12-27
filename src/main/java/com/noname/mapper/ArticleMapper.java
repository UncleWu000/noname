package com.noname.mapper;

import com.noname.entity.Admin;
import com.noname.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);

    List<Article> select(@Param("")Admin admin);

    List<Article> selectAllByRule(@Param("rule")String rule);
}