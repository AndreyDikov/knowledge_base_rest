package ru.knowledge_base_rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.knowledge_base_rest.entities.changes_history.ChangesHistory;

public interface ChangesHistoryRepository extends JpaRepository<ChangesHistory, Long> {
}
