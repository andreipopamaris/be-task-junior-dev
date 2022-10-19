package com.amaris.task.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.amaris.task.domain.Employee;
import com.amaris.task.domain.EmployeesReporsitory;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {
	@Mock
	private EmployeesReporsitory employeesReporsitory;
	
	@InjectMocks
	private EmployeeServiceImpl service;

	@Test
	void getAll() {
		// This test needs to pass as it is. No changes needed
		var e1 = Employee.builder().id(1L).name("Alice").build();
		var e2 = Employee.builder().id(2L).name("Bob").build();
		
		when(employeesReporsitory.findAll()).thenReturn(List.of(e1, e2));
		var users = service.getAllUsers();
		assertThat(users).hasSize(2);
		assertThat(users.get(0).getName()).isEqualTo("Alice");
		assertThat(users.get(1).getName()).isEqualTo("Bob");
	}
}
