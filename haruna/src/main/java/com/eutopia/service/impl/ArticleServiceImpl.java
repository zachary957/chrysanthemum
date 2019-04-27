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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;
import utils.MapperUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
        MapperUtils.beanCopy(articleDTO, articleDescription);
        articleDescription.setGmtCreate(new Date());
        articleDescriptionMapper.insertSelective(articleDescription);

        ArticleContent articleContent = new ArticleContent();
        MapperUtils.beanCopy(articleDTO, articleContent);
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
        MapperUtils.beanCopy(articleCommentDTO, articleComment);
        articleComment.setGmtCreate(new Date());
        articleCommentMapper.insertSelective(articleComment);
        return articleComment.getId();
    }

    @Override
    public Integer removeArticle(ArticleDTO articleDTO) {
        if (articleDescriptionMapper.deleteByUpdateState(articleDTO.getArticleDescriptionId()) > 0) {
            Example example = new Example(ArticleContent.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andCondition("article_description_id = ", articleDTO.getArticleDescriptionId());
            return articleContentMapper.deleteByUpdateState(articleContentMapper.selectOneByExample(example).getId());
        }
        return 0;
    }

    @Override
    public Integer removeArticleComment(ArticleCommentDTO articleCommentDTO) {
        return articleCommentMapper.deleteByUpdateState(articleCommentDTO.getId());
    }

    @Override
    public Integer modifyArticle(ArticleDTO articleDTO) {
        ArticleDescription articleDescription = new ArticleDescription();
        MapperUtils.beanCopy(articleDTO, articleDescription);
        articleDescription.setGmtModified(new Date());
        articleDescriptionMapper.updateByPrimaryKeySelective(articleDescription);

        Example example = new Example(ArticleContent.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andCondition("article_description_id = ", articleDTO.getArticleDescriptionId());
        ArticleContent articleContent = articleContentMapper.selectOneByExample(example);
        MapperUtils.beanCopy(articleDTO, articleContent);
        articleContent.setGmtModified(new Date());
        articleContentMapper.updateByPrimaryKeySelective(articleContent);
        return 1;
    }

    @Override
    public Integer modifyArticleComment(ArticleCommentDTO articleCommentDTO) {
        ArticleComment articleComment = new ArticleComment();
        MapperUtils.beanCopy(articleCommentDTO, articleComment);
        articleComment.setGmtModified(new Date());
        return articleCommentMapper.updateByPrimaryKeySelective(articleComment);
    }

    @Override
    public List<ArticleDTO> selecteArticle(ArticleDTO articleDTO) {
        if (articleDTO == null) {
            List<ArticleDTO> articleDTOList = new ArrayList<>();
            MapperUtils.batchBeanCopy(articleContentMapper.selectAll(), articleDTOList);
            MapperUtils.batchBeanCopy(articleDescriptionMapper.selectAll(), articleDTOList);
            return articleDTOList;
        }
        return null;
    }

    @Override
    public List<ArticleCommentDTO> selectArticleComment(ArticleCommentDTO articleCommentDTO) {
        List<ArticleCommentDTO> articleCommentDTOList = new ArrayList<>();
        BeanCopier beanCopier = BeanCopier.create(ArticleCommentDTO.class, ArticleComment.class, false);
        if (articleCommentDTO.getId() == null) {
            List<ArticleComment> articleCommentList = articleCommentMapper.selectAll();
            if (!CollectionUtils.isEmpty(articleCommentList)) {
                return null;
            }
        }
        return null;
    }
}
