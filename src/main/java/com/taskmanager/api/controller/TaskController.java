package com.taskmanager.api.controller;

import com.taskmanager.api.domain.Task;
import com.taskmanager.api.domain.TaskCreationData;
import com.taskmanager.api.domain.TaskListingData;
import com.taskmanager.api.domain.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskRepository repository;

    @GetMapping("/all")
    public Page<TaskListingData> listAllTasks(Pageable pagination) {
        return repository.findAll(pagination).map(TaskListingData::new);
    }

    @GetMapping
    public Page<TaskListingData> listByCompleted(Pageable pagination, @RequestParam boolean completed) {
        return completed ?
                repository.findAllByCompletedTrue(pagination).map(TaskListingData::new) :
                repository.findAllByCompletedFalse(pagination).map(TaskListingData::new);
    }

    @PostMapping
    @Transactional
    public void createTasks(@RequestBody TaskCreationData data) {
        repository.save(new Task(data));
    }
}
