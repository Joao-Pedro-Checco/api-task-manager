package com.taskmanager.api.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Task")
@Table(name = "tasks")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private boolean completed;

    public Task(TaskCreationData data) {
        this.title = data.title();
        this.description = data.description();
        this.completed = false;
    }

    public void updateInfo(TaskUpdatingData data) {
        if (data.title() != null) {
            this.title = data.title();
        }
        if (data.description() != null) {
            this.description = data.description();
        }
        if (data.completed() != null) {
            this.completed = data.completed();
        }
    }
}
