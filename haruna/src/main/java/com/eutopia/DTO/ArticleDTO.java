package com.eutopia.DTO;

import lombok.Data;

import java.io.Serializable;

//TODO 应该在客户端
@Data
public class ArticleDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer menuId;

    private String title;

    private String summary;

    private String source;

    private String author;

    private Integer sort;

    private String content;
}
