package com.amaris.task.controller;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amaris.task.model.TaskDto;
import com.amaris.task.service.TaskService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("task")
@AllArgsConstructor
public class TaskController {
	private final TaskService taskService;

	@GetMapping(path = "/unassigned")
	public List<TaskDto> getUnassignedTasks() {
		return taskService.getUnassignedTasks();
	}
	
	public List<TaskDto> getAssignedTasks() {
		return taskService.getUnassignedTasks();
	}
	
	public List<TaskDto> getAllTasks() {
		//TODO implement
		return Collections.emptyList();
	}
	
	@PutMapping(path = "/{taskId}", consumes = {MediaType.APPLICATION_JSON}, produces = {MediaType.APPLICATION_JSON})
	public TaskDto updateTask(@PathVariable("taskId") Long taskId, @RequestBody TaskDto taskDto) {
		return taskService.updateTask(taskDto);
	}
	
}
