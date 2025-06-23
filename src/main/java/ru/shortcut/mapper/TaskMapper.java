package ru.shortcut.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.shortcut.dto.TaskDto;
import ru.shortcut.entity.Task;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskMapper {

    TaskDto toDto(Task task);

    Task toEntity(TaskDto dto);
}
