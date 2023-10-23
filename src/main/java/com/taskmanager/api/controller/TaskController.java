package com.taskmanager.api.controller;

import com.taskmanager.api.domain.Task;
import com.taskmanager.api.domain.TaskCreationData;
import com.taskmanager.api.domain.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskRepository repository;

    @PostMapping
    @Transactional
    public void createTasks(@RequestBody TaskCreationData data) {
        repository.save(new Task(data));
    }
}
