package com.eutopia.service;

import com.eutopia.DTO.ArticleDTO;
import com.eutopia.entity.ArticleContent;
import com.eutopia.entity.ArticleDescription;
import com.eutopia.mapper.ArticleContentMapper;
import com.eutopia.mapper.ArticleDescriptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDescriptionMapper articleDescriptionMapper;

    @Autowired
    private ArticleContentMapper articleContentMapper;

    @Override
    public Integer addArticle(ArticleDTO articleDTO) {
        ArticleDescription articleDescription = new ArticleDescription();
        BeanCopier beanCopier = BeanCopier.create(ArticleDTO.class, ArticleDescription.class, false);
        beanCopier.copy(articleDTO, articleDescription, null);
        articleDescription.setGmtCreate(new Date());
        articleDescriptionMapper.insertSelective(articleDescription);

        ArticleContent articleContent = new ArticleContent();
        beanCopier = BeanCopier.create(ArticleDTO.class, ArticleContent.class, false);
        beanCopier.copy(articleDTO, articleContent, null);
        articleContent.setArticleDescriptionId(articleDescription.getId());
        articleContent.setGmtCreate(new Date());
        articleContentMapper.insertSelective(articleContent);

        return articleDescription.getId();
    }
}
