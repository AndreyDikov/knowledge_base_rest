package ru.knowledge_base_rest.entities.article;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.knowledge_base_rest.entities.changes_history.ChangesHistory;
import ru.knowledge_base_rest.entities.user.User;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "author_id")
    private User authorId;

    @Enumerated(EnumType.STRING)
    private Status statusId;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "type_id")
    private ArticleType typeId;

    @Column(name = "text")
    private String text;

    @OneToMany(mappedBy = "articleId", cascade = CascadeType.ALL)
    private List<ChangesHistory> history;
}
