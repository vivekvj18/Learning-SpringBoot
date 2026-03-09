package com.example.taskmanager.service;

import com.example.taskmanager.exception.TaskNotFoundException;
import org.springframework.stereotype.Service;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.dto.TaskRequestDTO;
import com.example.taskmanager.dto.TaskResponseDTO;
import com.example.taskmanager.entity.Task;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository)
    {
        this.taskRepository=taskRepository;
    }

    public TaskResponseDTO createTask(TaskRequestDTO requestDTO) {

        Task task = new Task();

        task.setTitle(requestDTO.getTitle());
        task.setDescription(requestDTO.getDescription());
        task.setStatus(requestDTO.getStatus());

        task.setCreatedAt(LocalDateTime.now());

        Task savedTask = taskRepository.save(task);

        TaskResponseDTO responseDTO = new TaskResponseDTO();

        responseDTO.setId(savedTask.getId());
        responseDTO.setTitle(savedTask.getTitle());
        responseDTO.setDescription(savedTask.getDescription());
        responseDTO.setStatus(savedTask.getStatus());
        responseDTO.setCreatedAt(savedTask.getCreatedAt());

        return responseDTO;

    }

    public List<TaskResponseDTO> getAllTasks()
    {
        List<Task> tasks = taskRepository.findAll();

        return tasks.stream()
                .map(task -> {
                    TaskResponseDTO dto = new TaskResponseDTO();
                    dto.setId(task.getId());
                    dto.setTitle(task.getTitle());
                    dto.setDescription(task.getDescription());
                    dto.setStatus(task.getStatus());
                    dto.setCreatedAt(task.getCreatedAt());
                    return dto;
                })
                .toList();
    }

    public TaskResponseDTO getTaskById(Long id){

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id " + id));

        TaskResponseDTO responseDTO = new TaskResponseDTO();

        responseDTO.setId(task.getId());
        responseDTO.setTitle(task.getTitle());
        responseDTO.setDescription(task.getDescription());
        responseDTO.setStatus(task.getStatus());
        responseDTO.setCreatedAt(task.getCreatedAt());

        return responseDTO;
    }

    public TaskResponseDTO updateTask(Long id, TaskRequestDTO requestDTO) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id " + id));

        task.setTitle(requestDTO.getTitle());
        task.setDescription(requestDTO.getDescription());
        task.setStatus(requestDTO.getStatus());

        Task updatedTask = taskRepository.save(task);

        TaskResponseDTO responseDTO = new TaskResponseDTO();

        responseDTO.setId(updatedTask.getId());
        responseDTO.setTitle(updatedTask.getTitle());
        responseDTO.setDescription(updatedTask.getDescription());
        responseDTO.setStatus(updatedTask.getStatus());
        responseDTO.setCreatedAt(updatedTask.getCreatedAt());

        return responseDTO;

    }

    public void deleteTask(Long id) {


        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id " + id));

        taskRepository.delete(task);
    }

}
