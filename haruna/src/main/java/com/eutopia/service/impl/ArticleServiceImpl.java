package com.eutopia.service.impl;

import com.eutopia.DTO.ArticleCommentDTO;
import com.eutopia.DTO.ArticleDTO;
import com.eutopia.entity.ArticleComment;
import com.eutopia.entity.ArticleContent;
import com.eutopia.entity.ArticleDescription;
import com.eutopia.exception.CheckException;
import com.eutopia.mapper.ArticleCommentMapper;
import com.eutopia.mapper.ArticleContentMapper;
import com.eutopia.mapper.ArticleDescriptionMapper;
import com.eutopia.mapper.MenuMapper;
import com.eutopia.service.ArticleService;
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

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    @Override
    public Integer addArticle(ArticleDTO articleDTO) {
        if (!menuMapper.existsWithPrimaryKey(articleDTO.getMenuId())) {
            throw new CheckException("指定菜单不存在");
        }

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

    @Override
    public Integer addArticleComment(ArticleCommentDTO articleCommentDTO) {
        if (!articleDescriptionMapper.existsWithPrimaryKey(articleCommentDTO.getArticleDescriptionId())) {
            throw new CheckException("指定文章不存在");
        }

        ArticleComment articleComment = new ArticleComment();
        BeanCopier beanCopier = BeanCopier.create(ArticleCommentDTO.class, ArticleComment.class, false);
        beanCopier.copy(articleCommentDTO, articleComment, null);
        articleComment.setGmtCreate(new Date());
        articleCommentMapper.insertSelective(articleComment);
        return articleComment.getId();
    }
}
