package com.example.taskmanager.controller;

import com.example.taskmanager.dto.TaskRequestDto;
import com.example.taskmanager.dto.TaskResponseDto;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // ================= CREATE TASK =================
    @PostMapping
    public TaskResponseDto createTask(@RequestBody TaskRequestDto request) {

        // DTO → Entity mapping
        boolean completed = "COMPLETED".equalsIgnoreCase(request.getStatus());

        Task task = new Task(
                request.getTitle(),
                request.getDescription(),
                completed
        );

        Task savedTask = taskService.createTask(task);

        // Entity → DTO mapping
        String status = savedTask.isCompleted() ? "COMPLETED" : "PENDING";

        return new TaskResponseDto(
                savedTask.getId(),
                savedTask.getTitle(),
                savedTask.getDescription(),
                status
        );
    }

    // ================= GET ALL TASKS =================
    @GetMapping
    public List<TaskResponseDto> getAllTasks() {

        return taskService.getAllTasks()
                .stream()
                .map(task -> new TaskResponseDto(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.isCompleted() ? "COMPLETED" : "PENDING"
                ))
                .collect(Collectors.toList());
    }

    // ================= GET TASK BY ID =================
    @GetMapping("/{id}")
    public TaskResponseDto getTaskById(@PathVariable Long id) {

        Task task = taskService.getTaskById(id);

        return new TaskResponseDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isCompleted() ? "COMPLETED" : "PENDING"
        );
    }
}