package ru.knowledge_base_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.knowledge_base_rest.entities.article.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    boolean existsByName(String name);
}
