package com.example.taskmanager.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    // status broken into 2 parts (internal representation)
    private boolean completed;
    private LocalDateTime completedAt;

    // mandatory for Hibernate
    public Task() {
        // Hibernate uses this via reflection
    }

    // constructor used by application code
    public Task(String title, String description, boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.completedAt = completed ? LocalDateTime.now() : null;
    }

    // ================= GETTERS =================

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    // ================= SETTERS =================

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * External-friendly setter:
     * Converts status string → internal boolean + time
     */
    public void setStatus(String status) {
        if ("COMPLETED".equalsIgnoreCase(status)) {
            this.completed = true;
            this.completedAt = LocalDateTime.now();
        } else {
            this.completed = false;
            this.completedAt = null;
        }
    }
}