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

import com.amaris.task.domain.Employee;
import com.amaris.task.model.EmployeeDto;
import com.amaris.task.service.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {
	private final EmployeeService employeeService;

	@GetMapping
	public ResponseEntity<List<EmployeeDto>> getEmployees() {
		return ResponseEntity.ok(employeeService.getAllUsers());
	}
	
	@GetMapping(path = "/getEmployeesWithTasks")
	public ResponseEntity<List<Employee>> getEmployeesWithTasks() {
		return ResponseEntity.ok(employeeService.getAllUsersWithAssignedTasks());
	}
	
	@GetMapping(path = "/{employeeId}")
	public ResponseEntity<Employee> getEmployeesById(@PathVariable("employeeId") Long employeeId) {
		Employee employee = employeeService.getEmployeeById(employeeId);
		return employee == null ? ResponseEntity.internalServerError().body(employee) : ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON}, produces = {MediaType.APPLICATION_JSON})
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
		return new ResponseEntity<EmployeeDto>(employeeService.createEmployee(employeeDto), HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/{employeeId}", consumes = {MediaType.APPLICATION_JSON}, produces = {MediaType.APPLICATION_JSON})
	public  ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("employeeId") Long employeeId, @RequestBody EmployeeDto employeeDto) {
		if(employeeService.getEmployeeById(employeeId) != null) {
			return ResponseEntity.ok(employeeService.updateEmployee(employeeDto));
		} else {
			return new ResponseEntity<EmployeeDto>(employeeService.createEmployee(employeeDto), HttpStatus.CREATED);
		}
	}
	
	@DeleteMapping(path = "/{employeeId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("employeeId") Long employeeId) {
		if(employeeService.getEmployeeById(employeeId) != null) {
			employeeService.deleteEmployee(employeeId);
			return ResponseEntity.ok(String.format("Employee with id %d deleted.", employeeId));
		} else {
			return ResponseEntity.internalServerError().body(String.format("There is no employee with id %d.", employeeId));		
		}
	}
}
