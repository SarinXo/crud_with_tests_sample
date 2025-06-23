package ru.shortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.shortcut.dto.TaskDto;
import ru.shortcut.service.TaskService;
import ru.shortcut.util.TaskDtoValidator;

import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/v1/task")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/title")
    public ResponseEntity<TaskDto> getByTitle(@RequestParam String title) {
        TaskDto taskDto = taskService.getTaskByTitle(title);

        return ResponseEntity.ok(taskDto);
    }

    @GetMapping("/status")
    public ResponseEntity<TaskDto> getByStatus(@RequestParam String status) {
        TaskDto taskDto = taskService.getTaskByStatus(status);

        return ResponseEntity.ok(taskDto);
    }

    @PostMapping("/create")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto dto) {
        if (!TaskDtoValidator.isValid(dto)) {
            TaskDto badDtoResponse = TaskDto.builder()
                    .title(StringUtils.hasText(dto.title()) ? dto.title() : "Title is required")
                    .status(StringUtils.hasText(dto.description()) ? dto.description() : "Status is required")
                    .description(StringUtils.hasText(dto.status()) ? dto.status() : "Description is required")
                    .build();

            return ResponseEntity.badRequest().body(badDtoResponse);
        }

        TaskDto taskDto = taskService.createTask(dto);

        return ResponseEntity.status(HttpStatus.OK).body(taskDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(
            @PathVariable Long id,
            @RequestBody TaskDto dto
    ) {
        TaskDto taskDto = taskService.updateTask(id, dto);

        return ResponseEntity.status(HttpStatus.OK).body(taskDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable Long id) {
        Boolean isDeleted = taskService.deleteTask(id);

        return ResponseEntity.status(HttpStatus.OK).body(isDeleted);
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskDto> dtos = taskService.getAllTask();

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

}
