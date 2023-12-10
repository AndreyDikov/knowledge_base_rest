package ru.knowledge_base_rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.knowledge_base_rest.entities.article.Article;
import ru.knowledge_base_rest.services.ArticleService;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/save")
    public Article saveArticle(Article article) {
        return articleService.saveArticle(article);
    }

    @GetMapping("/get/{id}")
    public Article getArticle(@PathVariable("id") Long id) {
        return articleService.getArticleById(id);
    }

    @GetMapping("/getAll")
    public List<Article> getAllArticles() {
        return articleService.showAllArticles();
    }

    @PostMapping("/edit/{id}")
    public Article updateArticle(@PathVariable("id") Long id, Article article) {
        return articleService.updateArticle(article);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteArticle(@PathVariable("id") Long id) {
        articleService.deleteArticleById(id);
    }
}
