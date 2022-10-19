package com.amaris.task.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TaskDto {
	private Long id;
	private String description;
	private LocalDate dueDate;
	private LocalDateTime creationDate;
	private LocalDateTime lastUpdate;
	private Long assignee;
}
