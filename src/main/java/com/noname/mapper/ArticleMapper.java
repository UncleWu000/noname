package com.noname.mapper;

import com.noname.entity.Admin;
import com.noname.entity.Article;
import com.noname.util.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper extends BaseMapper<Article>{

    List<Article> selectAllByRule(@Param("rule")String rule);
}