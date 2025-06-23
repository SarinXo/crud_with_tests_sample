package ru.shortcut.dto;

import lombok.Builder;

@Builder
public record TaskDto(
        String title,
        String description,
        String status
) {
}
