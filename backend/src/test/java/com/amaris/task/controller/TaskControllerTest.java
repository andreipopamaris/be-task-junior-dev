package com.amaris.task.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.amaris.task.domain.Task;
import com.amaris.task.domain.TaskRepository;
import com.amaris.task.service.TaskServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TaskControllerTest {
	
	@Mock
	private TaskRepository taskRepository;
	
	@InjectMocks
	private TaskServiceImpl service;
	
	@Test
	void getUnassignedTasks() {
		
		var dueDate = LocalDate.of(2022, 10, 20);
		var t1 = Task.builder()
						.id(1L)
						.assignee(null)
						.dueDate(dueDate)
						.description("Task One")
						.build();
		
		when(taskRepository.getAllUnassigned()).thenReturn(List.of(t1));
		
		var unassignedTasks = service.getUnassignedTasks();
		
		assertThat(unassignedTasks).hasSize(1);
		var dto = unassignedTasks.get(0);
		assertThat(dto.getAssignee()).isNull();
		assertThat(dto.getDescription()).isEqualTo("Task One");
		assertThat(dto.getDueDate()).isEqualTo(dueDate);
		
	}
	

}
