package ru.knowledge_base_rest.entities.security;

import jakarta.persistence.*;
import lombok.*;
import ru.knowledge_base_rest.entities.user.AppUser;

import java.util.HashSet;
import java.util.Set;

/**
 * Сущность, отвечающая за данные пользователя для аутентификации и авторизации.
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "security_user")
public class SecurityUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    @OneToOne(mappedBy = "securityUser",
            cascade = CascadeType.ALL)
    private AppUser appUser;
}

