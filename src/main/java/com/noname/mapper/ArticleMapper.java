package com.noname.mapper;

import com.noname.entity.Article;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.Mapper;


import java.util.List;

public interface ArticleMapper extends Mapper<Article> {

    List<Article> selectAllByRule(@Param("rule")String rule);
}