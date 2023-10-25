package com.taskmanager.api.controller;

import com.taskmanager.api.domain.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskRepository repository;

    @GetMapping("/all")
    public ResponseEntity<Page<TaskListingData>> listAllTasks(Pageable pagination) {
        var page = repository.findAll(pagination).map(TaskListingData::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping
    public ResponseEntity<Page<TaskListingData>> listByCompleted(Pageable pagination, @RequestParam boolean completed) {
        var page = completed ?
                repository.findAllByCompletedTrue(pagination).map(TaskListingData::new) :
                repository.findAllByCompletedFalse(pagination).map(TaskListingData::new);

        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity createTask(@RequestBody TaskCreationData data, UriComponentsBuilder uriBuilder) {
        var task = new Task(data);
        repository.save(task);
        var uri = uriBuilder.path("tasks/{id}").buildAndExpand(new TaskDetailingData(task)).toUri();

        return ResponseEntity.created(uri).body(new TaskDetailingData(task));
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateTask(@RequestBody TaskUpdatingData data) {
        var task = repository.getReferenceById(data.id());
        task.updateInfo(data);

        return ResponseEntity.ok(new TaskDetailingData(task));
    }
}
