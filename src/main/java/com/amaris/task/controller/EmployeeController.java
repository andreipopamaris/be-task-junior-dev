package com.amaris.task.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amaris.task.model.EmployeeDto;
import com.amaris.task.service.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {
	private final EmployeeService employeeService;

	@GetMapping
	public List<EmployeeDto> getEmployees() {
		return employeeService.getAllUsers();
	}
}
