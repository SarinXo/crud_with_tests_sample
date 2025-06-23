package ru.shortcut.util;

import org.springframework.util.StringUtils;
import ru.shortcut.dto.TaskDto;

public class TaskDtoValidator {

    public static boolean isValid(TaskDto taskDto) {
        return StringUtils.hasText(taskDto.title())
                && StringUtils.hasText(taskDto.status())
                && StringUtils.hasText(taskDto.description());
    }
}
