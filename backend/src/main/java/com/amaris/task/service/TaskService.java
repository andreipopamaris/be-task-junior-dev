package com.amaris.task.service;

import java.util.List;

import com.amaris.task.domain.Task;
import com.amaris.task.model.TaskDto;

public interface TaskService {
	
	List<TaskDto> getAllTasks();
	
	List<TaskDto> getAssignedTasks();
	
	List<TaskDto> getUnassignedTasks();

	TaskDto createTask(TaskDto taskDto);
	
	TaskDto updateTask(TaskDto taskDto);

	void deleteTask(Long taskId);

	TaskDto getTaskById(Long id);

	List<Task> getTasksByAssegnee(Long userId);

	void removeAssignee(List<Task> tasks);

}
