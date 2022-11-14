package com.amaris.task.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.amaris.task.model.EmployeeDto;
import com.amaris.task.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {
	
	@Mock
	private EmployeeService employeeService;
	
	@InjectMocks
	private EmployeeController employeeController;
	
	@Test
	void getEmployees() {
		
		var e1 = EmployeeDto.builder().id(1L).name("Alice").build();
		var e2 = EmployeeDto.builder().id(1L).name("Bob").build();
		when(employeeService.getAllUsers()).thenReturn(List.of(e1, e2));		
		
		var employees = employeeController.getEmployees();
		verify(employeeService, times(1)).getAllUsers();
		assertThat(employees).hasSize(2);
		
	}

}
