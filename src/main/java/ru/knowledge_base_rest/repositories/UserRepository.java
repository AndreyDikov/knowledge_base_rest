package ru.knowledge_base_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.knowledge_base_rest.entities.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
