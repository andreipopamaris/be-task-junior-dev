package com.amaris.task.service;

import java.util.List;

import com.amaris.task.model.TaskDto;

public interface TaskService {
	
	List<TaskDto> getAllTasks();
	
	List<TaskDto> getAssignedTasks();
	
	List<TaskDto> getUnassignedTasks();
	
	TaskDto updateTask(TaskDto taskDto);
	
	// create and delete can be added

}
