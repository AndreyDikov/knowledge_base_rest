package ru.knowledge_base_rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.knowledge_base_rest.entities.article.Article;
import ru.knowledge_base_rest.entities.changes_history.ChangesHistory;
import ru.knowledge_base_rest.repositories.ArticleRepository;
import ru.knowledge_base_rest.repositories.ChangesHistoryRepository;

import java.util.Calendar;
import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ChangesHistoryRepository changesHistoryRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository,
                          ChangesHistoryRepository changesHistoryRepository) {
        this.articleRepository = articleRepository;
        this.changesHistoryRepository = changesHistoryRepository;
    }

    public List<Article> showAllArticles() {
        return articleRepository.findAll();
    }

    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article saveArticle(Article article) {
        if (!articleRepository.existsByName(article.getName())) {
            ChangesHistory changesHistory = new ChangesHistory();
            changesHistory.setUserId(article.getAuthorId());
            changesHistory.setArticleId(article);
            changesHistory.setVersion(1);
            changesHistory.setDate(Calendar.getInstance().getTime());
            changesHistory.setNewText(article.getText());
            changesHistoryRepository.save(changesHistory);
            return articleRepository.save(article);
        }
        return null;
    }

    public Article updateArticle(Article article) {
        if (articleRepository.existsById(article.getId())) {
            ChangesHistory changesHistory = new ChangesHistory();
            changesHistory.setUserId(article.getAuthorId());
            changesHistory.setArticleId(article);
            changesHistory.setVersion(article.getHistory().size() + 1);
            changesHistory.setDate(Calendar.getInstance().getTime());
            changesHistory.setNewText(article.getText());
            changesHistoryRepository.save(changesHistory);
            return articleRepository.save(article);
        }
        return null;
    }

    public void deleteArticleById(Long id) {
        articleRepository.deleteById(id);
    }
}
