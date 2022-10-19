package com.amaris.task.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmployeeDto {
	private Long id;
	private String name;
}
