package com.amaris.task.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.amaris.task.domain.Task;
import com.amaris.task.domain.TaskRepository;
import com.amaris.task.model.TaskDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {
	private final TaskRepository taskRepository;

	@Override
	public List<TaskDto> getAllTasks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TaskDto> getAssignedTasks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TaskDto> getUnassignedTasks() {
		var tasks = taskRepository.getAllUnassigned();
		return tasks.stream().map(this::entityToDto).toList();
	}

	@Override
	public TaskDto updateTask(TaskDto taskDto) {
		// TODO Auto-generated method stub
		System.out.println(taskDto.getDescription());
		return null;
	}
	
	private TaskDto entityToDto(Task entity) {
		return TaskDto.builder()
				.id(entity.getId())
				.assignee(Objects.nonNull(entity.getAssignee()) ? entity.getAssignee().getId() : null)
				.creationDate(entity.getCreationDate())
				.lastUpdate(entity.getLastUpdate())
				.dueDate(entity.getDueDate())
				.description(entity.getDescription())
				.build();
	}
	
	private Task dtoToEntity(TaskDto dto) {
		//TODO implementation
		return null;
	}

}
