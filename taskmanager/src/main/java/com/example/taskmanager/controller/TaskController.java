package com.example.taskmanager.controller;

import com.example.taskmanager.service.TaskService;
import org.springframework.web.bind.annotation.*;

import com.example.taskmanager.dto.TaskRequestDTO;
import com.example.taskmanager.dto.TaskResponseDTO;

import java.util.List;

@RestController
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    public TaskResponseDTO createTask(@RequestBody TaskRequestDTO requestDTO)
    {
        return taskService.createTask(requestDTO);
    }

    @GetMapping("/tasks")
    public List<TaskResponseDTO> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/tasks/{id}")
    public TaskResponseDTO getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/tasks/{id}")
    public TaskResponseDTO updateTask(@PathVariable Long id,
                                      @RequestBody TaskRequestDTO requestDTO) {
        return taskService.updateTask(id, requestDTO);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
