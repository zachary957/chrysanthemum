package com.eutopia;

import com.eutopia.DTO.ArticleDTO;
import org.springframework.cglib.beans.BeanCopier;

public class TestBeanCopier {

    public static void main(String[] args) {
        //source赋值给target
        ArticleDTO source = new ArticleDTO();
        source.setAuthor("AAA");
        ArticleDTO target = new ArticleDTO();
        target.setAuthor("BBB");
        BeanCopier beanCopier = BeanCopier.create(ArticleDTO.class, ArticleDTO.class, false);
        beanCopier.copy(source, target, null);
        System.out.println(target);
    }
}
