package com.lixiaohao.test.springshiro.controller;

import com.lixiaohao.test.springshiro.model.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {


    @RequestMapping("find")
    public List<Article> findArticles(){

        Article article = new Article();
        article.setAuthor("张三");
        article.setName("如何不睡觉");

        List<Article> articles = new ArrayList<Article>();
        articles.add(article);

        return articles;

    }

}
