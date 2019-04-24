package com.eutopia.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleDescription {

    private Integer id;

    private String title;

    private String source;

    private String author;

    private Integer sort;

    private Date gmtCreate;

    private Date gmtModified;

    private Boolean deleted;
}
