package com.eutopia.entity;

import com.eutopia.entity.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ArticleComment extends BaseEntity {

    private Integer articleDescriptionId;

    private String content;

    private String nickName;
}
