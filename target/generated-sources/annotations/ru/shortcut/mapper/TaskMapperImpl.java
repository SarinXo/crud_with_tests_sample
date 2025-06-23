package ru.shortcut.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.shortcut.dto.TaskDto;
import ru.shortcut.entity.Task;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-14T01:09:32+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.3 (BellSoft)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public TaskDto toDto(Task task) {
        if ( task == null ) {
            return null;
        }

        TaskDto.TaskDtoBuilder taskDto = TaskDto.builder();

        taskDto.title( task.getTitle() );
        taskDto.description( task.getDescription() );
        taskDto.status( task.getStatus() );

        return taskDto.build();
    }

    @Override
    public Task toEntity(TaskDto dto) {
        if ( dto == null ) {
            return null;
        }

        Task.TaskBuilder task = Task.builder();

        task.title( dto.title() );
        task.description( dto.description() );
        task.status( dto.status() );

        return task.build();
    }
}
