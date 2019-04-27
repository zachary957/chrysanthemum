package com.eutopia.service;

import com.eutopia.DTO.ArticleCommentDTO;
import com.eutopia.DTO.ArticleDTO;

import java.util.List;

public interface ArticleService {

    Integer addArticle(ArticleDTO articleDTO);

    Integer addArticleComment(ArticleCommentDTO articleCommentDTO);

    Integer removeArticle(ArticleDTO articleDTO);

    Integer removeArticleComment(ArticleCommentDTO articleCommentDTO);

    Integer modifyArticle(ArticleDTO articleDTO);

    Integer modifyArticleComment(ArticleCommentDTO articleCommentDTO);

    List<ArticleDTO> selecteArticle(ArticleDTO articleDTO);

    List<ArticleCommentDTO> selectArticleComment(ArticleCommentDTO articleCommentDTO);
}
