package com.eutopia;

import com.eutopia.DTO.ArticleCommentDTO;
import com.eutopia.DTO.ArticleDTO;
import com.eutopia.DTO.MenuDTO;
import com.eutopia.service.ArticleService;
import com.eutopia.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootTest
@RunWith(SpringRunner.class)
@MapperScan("com.eutopia.mapper")
public class TestMybatis {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private MenuService menuService;

    @Test
    public void testArticleService() {
        //增加一个栏目
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setMenuDescription("测试栏目");
        menuDTO.setSort(0);
        Integer menuId = menuService.addMenu(menuDTO);

        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setAuthor("不睡等着猝死吧");
        articleDTO.setContent("测完就溜了");
        articleDTO.setMenuId(menuId);
        //articleDTO.setMenuId(99999);
        articleDTO.setSort(0);
        articleDTO.setSource("原创");
        articleDTO.setSummary("修仙");
        articleDTO.setTitle("我已成仙");
        Integer articleDescriptionId = articleService.addArticle(articleDTO);

        ArticleCommentDTO articleCommentDTO = new ArticleCommentDTO();
        articleCommentDTO.setArticleDescriptionId(articleDescriptionId);
        //articleCommentDTO.setArticleDescriptionId(99999);
        articleCommentDTO.setContent("嗯?");
        articleCommentDTO.setNickName("好困");
        System.out.println(articleService.addArticleComment(articleCommentDTO));
    }
}
