package com.eutopia.DTO;

import lombok.Data;

//TODO 应该在客户端
@Data
public class ArticleDTO {

    private Integer menuId;

    private String title;

    private String summary;

    private String source;

    private String author;

    private Integer sort;

    private String content;
}
