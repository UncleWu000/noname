package com.noname.service.impl;

import com.noname.entity.Article;
import com.noname.mapper.ArticleMapper;
import com.noname.service.ArticleService;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends BaseServiceImpl<ArticleMapper, Article> implements ArticleService{
}
