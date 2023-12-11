package ru.knowledge_base_rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.knowledge_base_rest.entities.article.Article;
import ru.knowledge_base_rest.entities.changes_history.ChangesHistory;
import ru.knowledge_base_rest.entities.user.User;
import ru.knowledge_base_rest.repositories.ArticleRepository;
import ru.knowledge_base_rest.repositories.ChangesHistoryRepository;
import ru.knowledge_base_rest.repositories.UserRepository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ChangesHistoryRepository changesHistoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository,
                          ChangesHistoryRepository changesHistoryRepository,
                          UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.changesHistoryRepository = changesHistoryRepository;
        this.userRepository = userRepository;
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

    public Article updateArticle(Article temporaryArticle) {
        Optional<Article> optional = articleRepository
                .findByName(temporaryArticle.getName());
        if (optional.isPresent()) {
            Article article = optional.get();
            ChangesHistory changesHistory = new ChangesHistory();
            changesHistory.setUserId(getUserConnectedToDB(temporaryArticle.getAuthorId()));
            changesHistory.setArticleId(article);
            changesHistory.setVersion(article.getHistory().size() + 1);
            changesHistory.setDate(Calendar.getInstance().getTime());
            changesHistory.setNewText(temporaryArticle.getText());
            changesHistoryRepository.save(changesHistory);
            article.setName(temporaryArticle.getName());
            article.setStatusId(temporaryArticle.getStatusId());
            article.setText(temporaryArticle.getText());
            article.setAuthorId(getUserConnectedToDB(temporaryArticle.getAuthorId()));
            return articleRepository.save(article);
        }
        return null;
    }

    private User getUserConnectedToDB(User user) {
        Optional<User> optional = userRepository
                .findById(user.getId());
        if (optional.isPresent()) {
            User userFromDb = optional.get();
            userFromDb.setPostId(user.getPostId());
            userFromDb.setDepartmentId(user.getDepartmentId());
            userFromDb.setDateEmployment(user.getDateEmployment());
            userFromDb.setDateDismissal(user.getDateDismissal());
            userRepository.save(userFromDb);
            return userFromDb;
        }
        userRepository.save(user);
        return user;
    }

    public boolean deleteArticleById(Long id) {
        Optional<Article> optional = articleRepository.findById(id);
        if (optional.isPresent()) {
            Article article = optional.get();
            article.getHistory().forEach((history) -> history.setArticleId(null));
            articleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
