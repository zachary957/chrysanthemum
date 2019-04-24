package com.eutopia;

import com.eutopia.entity.ArticleDescription;
import com.eutopia.mapper.ArticleDescriptionMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
@MapperScan("com.eutopia.mapper")
public class TestMybatis {

    @Autowired
    private ArticleDescriptionMapper articleDescriptionMapper;

    @Test
    public void testSQL() {
        ArticleDescription articleDescription = new ArticleDescription();
        articleDescription.setTitle("测试一下");
        articleDescription.setAuthor("酱油");
        articleDescription.setSource("自测");
        articleDescription.setSort(0);
        articleDescription.setGmtCreate(new Date());
        articleDescriptionMapper.insert(articleDescription);
        System.out.println(articleDescriptionMapper.selectAll());
        articleDescription.setGmtModified(new Date());
        articleDescription.setTitle("我变了");
        articleDescription.setId(7);
        articleDescriptionMapper.update(articleDescription);
        System.out.println(articleDescriptionMapper.selectAll());
    }
}
