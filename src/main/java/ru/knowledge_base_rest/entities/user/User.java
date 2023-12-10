package ru.knowledge_base_rest.entities.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.knowledge_base_rest.entities.article.Article;
import ru.knowledge_base_rest.entities.changes_history.ChangesHistory;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "post_id")
    private Post postId;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "department_id")
    private Department departmentId;

    @OneToMany(mappedBy = "authorId", cascade = CascadeType.ALL)
    private List<Article> articles;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<ChangesHistory> history;

    @Column(name = "date_employment")
    private Date dateEmployment;

    @Column(name = "date_dismissal")
    private Date dateDismissal;
}
