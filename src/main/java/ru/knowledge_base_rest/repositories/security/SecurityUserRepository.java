package ru.knowledge_base_rest.repositories.security;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.knowledge_base_rest.entities.security.SecurityUser;

import java.util.Optional;

public interface SecurityUserRepository extends JpaRepository<SecurityUser, Long> {

    Optional<SecurityUser> findByUsername(String username);
}
