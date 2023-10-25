package com.taskmanager.api.domain;

public record TaskDetailingData(Long id, String title, String description, boolean completed) {
    public TaskDetailingData(Task task) {
        this(task.getId(), task.getTitle(), task.getDescription(), task.isCompleted());
    }
}
