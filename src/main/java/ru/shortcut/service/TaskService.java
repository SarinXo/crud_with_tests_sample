package ru.shortcut.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shortcut.dto.TaskDto;
import ru.shortcut.entity.Task;
import ru.shortcut.exceptions.TaskNotFoundException;
import ru.shortcut.mapper.TaskMapper;
import ru.shortcut.repository.TaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskDto getTaskByTitle(String title) {
        return taskRepository.findByTitle(title)
                .map(taskMapper::toDto)
                .orElseThrow(TaskNotFoundException::new);
    }

    public TaskDto getTaskByStatus(String status) {
        return taskRepository.findByStatus(status)
                .map(taskMapper::toDto)
                .orElseThrow(TaskNotFoundException::new);
    }

    public TaskDto createTask(TaskDto dto) {
        Task task = taskMapper.toEntity(dto);
        Task savedTask = taskRepository.save(task);

        return taskMapper.toDto(savedTask);
    }

    public TaskDto updateTask(Long id, TaskDto dto) {
        Task entity = taskRepository.findById(id)
                .orElseThrow(TaskNotFoundException::new);

        entity.setTitle(dto.title());
        entity.setDescription(dto.description());
        entity.setStatus(dto.status());

        Task savedEntity = taskRepository.save(entity);
        return taskMapper.toDto(savedEntity);
    }

    public boolean deleteTask(Long id) {
        boolean exists = taskRepository.existsById(id);

        if (!exists)
            throw new TaskNotFoundException();

        taskRepository.deleteById(id);
        return true;
    }

    public List<TaskDto> getAllTask() {
        return taskRepository.findAll().stream()
                .map(taskMapper::toDto)
                .toList();
    }
}
