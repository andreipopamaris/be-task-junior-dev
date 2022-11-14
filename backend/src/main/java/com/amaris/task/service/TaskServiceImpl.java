package com.amaris.task.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.amaris.task.domain.Employee;
import com.amaris.task.domain.EmployeesReporsitory;
import com.amaris.task.domain.Task;
import com.amaris.task.domain.TaskRepository;
import com.amaris.task.model.TaskDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {
	private final TaskRepository taskRepository;
	private final EmployeesReporsitory employeesReporsitory;

	@Override
	public List<TaskDto> getAllTasks() {
		// TODO Auto-generated method stub
		return taskRepository.findAll().stream().map(this::entityToDto).toList();
	}

	@Override
	public List<TaskDto> getAssignedTasks() {
		// TODO Auto-generated method stub
		return taskRepository.getAllAssigned().stream().map(this::entityToDto).toList();
	}

	@Override
	public List<TaskDto> getUnassignedTasks() {
		var tasks = taskRepository.getAllUnassigned();
		return tasks.stream().map(this::entityToDto).toList();
	}
	
	@Override
	public TaskDto getTaskById(Long id) {
		Optional<Task> task = taskRepository.findById(id);
		return task.isPresent() ? entityToDto(task.get()) : null;
	}
	
	@Override
	public List<Task> getTasksByAssegnee(Long userId) {
		return taskRepository.getAllByAssegnee(userId);
	}
	
	@Override
	public TaskDto createTask(TaskDto taskDto) {
		// TODO Auto-generated method stub
		Task taskToUpdate = dtoToEntity(taskDto);
		return entityToDto(taskRepository.save(taskToUpdate));
	}

	@Override
	public TaskDto updateTask(TaskDto taskDto) {
		// TODO Auto-generated method stub
		Task taskToUpdate = dtoToEntity(taskDto);
		taskRepository.updateTask(taskToUpdate.getId(), taskToUpdate.getDescription(), taskToUpdate.getDueDate(), taskToUpdate.getLastUpdate(), taskToUpdate.getAssignee());
		return getTaskById(taskToUpdate.getId());
		
	}
	
	@Override
	public void deleteTask(Long taskId) {
		// TODO Auto-generated method stub
		taskRepository.deleteById(taskId);
	}
	
	@Override
	public void removeAssignee(List<Task> tasks) {
		// TODO Auto-generated method stub
		for(Task task : tasks) {
			taskRepository.removeAssignee(task.getId());
		}
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
		Optional<Employee> emp = null;
		if(dto.getAssignee() != null) {
			emp = employeesReporsitory.findById(dto.getAssignee());
		}
		//TODO implementation
		return Task.builder()
				.id(dto.getId())
				.description(dto.getDescription())
				.dueDate(dto.getDueDate())
				.assignee(emp != null && emp.isPresent() ? emp.get() : null)
				.creationDate(dto.getCreationDate())
				.lastUpdate(dto.getLastUpdate())
				.build();
	}

}
