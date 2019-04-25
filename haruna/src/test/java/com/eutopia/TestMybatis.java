package com.eutopia;

import com.eutopia.DTO.ArticleDTO;
import com.eutopia.entity.ArticleComment;
import com.eutopia.entity.ArticleContent;
import com.eutopia.entity.ArticleDescription;
import com.eutopia.entity.Menu;
import com.eutopia.mapper.ArticleCommentMapper;
import com.eutopia.mapper.ArticleContentMapper;
import com.eutopia.mapper.ArticleDescriptionMapper;
import com.eutopia.mapper.MenuMapper;
import com.eutopia.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import tk.mybatis.spring.annotation.MapperScan; //tk
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

    @Autowired
    private ArticleContentMapper articleContentMapper;

    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private ArticleService articleService;

    @Test
    public void testAddArticleAndComment() {
        //增加一个栏目
        Menu menu = new Menu();
        menu.setMenuDescription("测试栏目");
        menu.setSort(0);
        menu.setGmtCreate(new Date());
        menuMapper.insertSelective(menu);

        //增加一个文章的描述
        ArticleDescription articleDescription = new ArticleDescription();
        articleDescription.setMenuId(menu.getId());
        articleDescription.setTitle("通用mapper的坑");
        articleDescription.setAuthor("影灯笼");
        articleDescription.setSource("原创");
        articleDescription.setSummary("你中招了没?");
        articleDescription.setSort(0);
        articleDescription.setGmtCreate(new Date());
        articleDescriptionMapper.insertSelective(articleDescription);

        //增加对应文章的内容
        ArticleContent articleContent = new ArticleContent();
        articleContent.setArticleDescriptionId(articleDescription.getId());
        articleContent.setContent("要加@GeneratedValue(generator = \"JDBC\")才能get到id啊,感觉不太优雅...");
        articleContent.setGmtCreate(new Date());
        articleContentMapper.insertSelective(articleContent);

        //为对应的文章留10条评论
        for (int i = 0; i < 10; i++) {
            ArticleComment articleComment = new ArticleComment();
            articleComment.setArticleDescriptionId(articleDescription.getId());
            articleComment.setContent("学到了,谢谢茄子");
            articleComment.setNickName("酱油男");
            articleComment.setGmtCreate(new Date());
            articleCommentMapper.insertSelective(articleComment);
        }
    }

    @Test
    public void testArticleService() {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setAuthor("不睡等着猝死吧");
        articleDTO.setContent("测完就溜了");
        articleDTO.setMenuId(0);
        articleDTO.setSort(0);
        articleDTO.setSource("原创");
        articleDTO.setSummary("修仙");
        articleDTO.setTitle("我已成仙");
        System.out.println(articleService.addArticle(articleDTO));
    }
}
