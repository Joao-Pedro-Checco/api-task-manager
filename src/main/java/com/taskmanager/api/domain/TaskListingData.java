package com.taskmanager.api.domain;

public record TaskListingData(Long id, String title, String description, boolean completed) {
    public TaskListingData(Task task) {
        this(task.getId(), task.getTitle(), task.getDescription(), task.isCompleted());
    }
}
