package com.eutopia.mapper;

import com.eutopia.entity.Article;
import com.eutopia.register.MyMapper;
import org.apache.ibatis.annotations.Param;

public interface ArticleMapper extends MyMapper<Article> {

    Article selectArticleById(@Param("id") Integer id);
}
