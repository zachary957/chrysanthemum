package com.eutopia;

import com.eutopia.entity.Article;
import com.eutopia.mapper.ArticleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
@MapperScan("com.eutopia.mapper")
public class TestMybatis {

    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void testArticleService() {

    }

    @Test
    public void testDelete() {
        Article article = new Article();
        article.setAuthor("123");
        article.setContent("123");
        article.setGmtCreate(new Date());
        article.setMenuId(123);
        article.setSource("123");
        article.setTitle("123");
        article.setSummary("123");
        article.setSort(0);
        articleMapper.insertSelective(article);
    }

    @Test
    public void testMyMapper() {
        articleMapper.deleteByPrimaryKey(3);
    }
}
