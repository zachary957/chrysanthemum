package com.eutopia.mapper;

import com.eutopia.entity.ArticleDescription;
import com.eutopia.register.MyMapper;
import org.apache.ibatis.annotations.Param;

public interface ArticleDescriptionMapper extends MyMapper<ArticleDescription> {

    ArticleDescription selectArticleById(@Param("id") Integer id);
}
