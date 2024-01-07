package ru.knowledge_base_rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.knowledge_base_rest.entities.article.Article;
import ru.knowledge_base_rest.entities.changes_history.ChangesHistory;
import ru.knowledge_base_rest.entities.user.AppUser;
import ru.knowledge_base_rest.repositories.ArticleRepository;
import ru.knowledge_base_rest.repositories.ChangesHistoryRepository;
import ru.knowledge_base_rest.repositories.appUser.AppUserRepository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ChangesHistoryRepository changesHistoryRepository;
    private final AppUserRepository userRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository,
                          ChangesHistoryRepository changesHistoryRepository,
                          AppUserRepository userRepository) {
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
            changesHistory.setAppUserId(article.getAuthorId());
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
            changesHistory.setAppUserId(getUserConnectedToDB(temporaryArticle.getAuthorId()));
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

    private AppUser getUserConnectedToDB(AppUser appUser) {
        Optional<AppUser> optional = userRepository
                .findById(appUser.getId());
        if (optional.isPresent()) {
            AppUser appUserFromDb = optional.get();
            appUserFromDb.setPostId(appUser.getPostId());
            appUserFromDb.setDepartmentId(appUser.getDepartmentId());
            appUserFromDb.setDateEmployment(appUser.getDateEmployment());
            appUserFromDb.setDateDismissal(appUser.getDateDismissal());
            userRepository.save(appUserFromDb);
            return appUserFromDb;
        }
        userRepository.save(appUser);
        return appUser;
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
