package com.lixiaohao.test.springshiro.controller;

import com.lixiaohao.test.springshiro.model.Article;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("article")
public class ArticleController {


    @RequestMapping("find")
    @ResponseBody
    public ResponseEntity<List<Article>> findArticles(){

        Article article = new Article();
        article.setAuthor("张三");
        article.setName("如何不睡觉");

        List<Article> articles = new ArrayList<Article>();
        articles.add(article);

        return new ResponseEntity<List<Article>>(articles, HttpStatus.OK);

    }



}
