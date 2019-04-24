package com.eutopia.mapper;

import com.eutopia.entity.ArticleDescription;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ArticleDescriptionMapper {

    @Select("SELECT * FROM article_description")
    @Results({
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "deleted", column = "is_deleted")
    })
    List<ArticleDescription> selectAll();

    @Select("SELECT * FROM article_description WHERE id = #{id}")
    @Results({
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified"),
            @Result(property = "deleted", column = "is_deleted")
    })
    ArticleDescription selectById(int id);

    @Insert("INSERT INTO article_description(title, source, author, sort, gmt_create) VALUES (#{title}, #{source}, #{author}, #{sort}, #{gmtCreate, jdbcType=TIMESTAMP})")
    Integer insert(ArticleDescription articleDescription);

    @Delete("DELETE FROM article_description WHERE id = #{id}")
    Integer deleteById(int id);

    @Update("UPDATE article_description SET title = #{title}, source = #{source}, author = #{author}, sort = #{sort}, gmt_modified = #{gmtModified} where id = #{id}")
    Integer update(ArticleDescription articleDescription);
}
