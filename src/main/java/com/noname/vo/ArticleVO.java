package com.noname.vo;

import com.noname.entity.Article;
import com.noname.util.DateUtils;

public class ArticleVO extends Article {

    public String getCreateDateStr(){
        if(getCreateDate()!=null){
            return DateUtils.format(getCreateDate(), DateUtils.YMD);
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
