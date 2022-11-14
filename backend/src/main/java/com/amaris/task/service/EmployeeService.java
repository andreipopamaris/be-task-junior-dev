package com.amaris.task.service;

import java.util.List;

import com.amaris.task.domain.Employee;
import com.amaris.task.model.EmployeeDto;

public interface EmployeeService {

	public List<EmployeeDto> getAllUsers();

	public List<Employee> getAllUsersWithAssignedTasks();

	public Employee getEmployeeById(Long employeeId);

	public EmployeeDto createEmployee(EmployeeDto employeeDto);

	public EmployeeDto updateEmployee(EmployeeDto employeeDto);

	public void deleteEmployee(Long employeeId);
}
