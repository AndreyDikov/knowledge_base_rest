package ru.knowledge_base_rest.repositories.appUser;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.knowledge_base_rest.entities.user.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
}
