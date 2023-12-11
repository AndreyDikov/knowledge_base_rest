package ru.knowledge_base_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.knowledge_base_rest.entities.article.Article;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    boolean existsByName(String name);

    Optional<Article> findByName(String name);
}
