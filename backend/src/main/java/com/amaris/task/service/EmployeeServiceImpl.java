package com.amaris.task.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.amaris.task.domain.Employee;
import com.amaris.task.domain.EmployeesReporsitory;
import com.amaris.task.domain.Task;
import com.amaris.task.domain.TaskRepository;
import com.amaris.task.model.EmployeeDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	private final EmployeesReporsitory employeesReporsitory;
	private final TaskRepository taskRepository;

	@Override
	public List<EmployeeDto> getAllUsers() {
		var employees = employeesReporsitory.findAll();
		return employees.stream().map(this::entityToDto).toList();
	}
	
	@Override
	public List<Employee> getAllUsersWithAssignedTasks() {
		return employeesReporsitory.findAll();
	}
	
	@Override
	public Employee getEmployeeById(Long employeeId) {
		Optional<Employee> employee = employeesReporsitory.findById(employeeId);
		return employee.isPresent() ? employee.get() : null;
	}
	
	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		// TODO Auto-generated method stub
		return entityToDto(employeesReporsitory.save(dtoToEntity(employeeDto)));
	}
	
	@Override
	public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
		// TODO Auto-generated method stub
		return entityToDto(employeesReporsitory.save(dtoToEntity(employeeDto)));
	}
	
	@Override
	public void deleteEmployee(Long employeeId) {
		// TODO Auto-generated method stub
		Employee emp = getEmployeeById(employeeId);
		for(Task t : emp.getTasks())
			taskRepository.removeAssignee(t.getId());
		employeesReporsitory.deleteById(employeeId);
	}
	
	private EmployeeDto entityToDto(Employee entity) {
		return EmployeeDto.builder().id(entity.getId()).name(entity.getName()).build();
	}
	
	private Employee dtoToEntity(EmployeeDto dto) {
		List<Task> tasks = null;
		if(dto.getId() != null) 
			tasks = taskRepository.getAllByAssegnee(dto.getId());
		
		//TODO implementation
		return Employee.builder()
				.id(dto.getId())
				.name(dto.getName())
				.tasks(tasks)
				.build();
	}
}
