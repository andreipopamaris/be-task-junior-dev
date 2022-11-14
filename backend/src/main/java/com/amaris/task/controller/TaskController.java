package com.amaris.task.controller;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping(path = "/all")
	public ResponseEntity<List<TaskDto>> getAllTasks() {
		return ResponseEntity.ok(taskService.getAllTasks());
	}
	
	@GetMapping(path = "/unassigned")
	public ResponseEntity<List<TaskDto>> getUnassignedTasks() {
		
		return ResponseEntity.ok(taskService.getUnassignedTasks());
	}
	
	@GetMapping(path = "/assigned")
	public ResponseEntity<List<TaskDto>> getAssignedTasks() {
		return ResponseEntity.ok(taskService.getAssignedTasks());
	}
	
	@GetMapping(path = "/{taskId}")
	public ResponseEntity<TaskDto> getById(@PathVariable("taskId") Long taskId) {
		TaskDto task = taskService.getTaskById(taskId);
		return task == null ? ResponseEntity.internalServerError().body(task) : ResponseEntity.ok(task);
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON}, produces = {MediaType.APPLICATION_JSON})
	public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
		return new ResponseEntity<TaskDto>(taskService.createTask(taskDto), HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/{taskId}", consumes = {MediaType.APPLICATION_JSON}, produces = {MediaType.APPLICATION_JSON})
	public ResponseEntity<TaskDto> updateTask(@PathVariable("taskId") Long taskId, @RequestBody TaskDto taskDto) {
		if(taskService.getTaskById(taskId) != null) {
			return ResponseEntity.ok(taskService.updateTask(taskDto));
		} else {
			return new ResponseEntity<TaskDto>(taskService.createTask(taskDto), HttpStatus.CREATED);
		}
	}
	
	@DeleteMapping(path = "/{taskId}")
	public ResponseEntity<String> deleteTask(@PathVariable("taskId") Long taskId) {
		if(taskService.getTaskById(taskId) != null) {
			taskService.deleteTask(taskId);
			return ResponseEntity.ok(String.format("Task with id %d deleted.", taskId));
		} else {
			return ResponseEntity.internalServerError().body(String.format("There is no task with id %d.", taskId));		
		}
	}
}
