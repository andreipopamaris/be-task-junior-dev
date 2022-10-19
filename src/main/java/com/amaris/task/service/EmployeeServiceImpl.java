package com.amaris.task.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amaris.task.domain.Employee;
import com.amaris.task.domain.EmployeesReporsitory;
import com.amaris.task.model.EmployeeDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	private final EmployeesReporsitory employeesReporsitory;
	

	@Override
	public List<EmployeeDto> getAllUsers() {
		var employees = employeesReporsitory.findAll();
		return employees.stream().map(this::entityToDto).toList();
	}
	
	private EmployeeDto entityToDto(Employee entity) {
		return EmployeeDto.builder().id(entity.getId()).name(entity.getName()).build();
	}
}
