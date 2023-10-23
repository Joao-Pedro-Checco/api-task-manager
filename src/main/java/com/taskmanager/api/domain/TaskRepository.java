package com.taskmanager.api.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findAllByCompletedTrue(Pageable pagination);

    Page<Task> findAllByCompletedFalse(Pageable pagination);
}
