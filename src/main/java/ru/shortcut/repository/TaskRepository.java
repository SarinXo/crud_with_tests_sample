package ru.shortcut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shortcut.entity.Task;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTitle(String title);

    Optional<Task> findByStatus(String status);
}
