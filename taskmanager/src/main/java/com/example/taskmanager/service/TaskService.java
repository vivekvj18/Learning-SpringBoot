package com.example.taskmanager.service;

import com.example.taskmanager.entity.Task;

import java.util.List;

public interface TaskService {

    Task createTask(Task task);

    List<Task> getAllTasks();

    Task getTaskById(Long id);
}
