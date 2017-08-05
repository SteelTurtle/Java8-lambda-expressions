package org.gorillacorp.flatmaps;

import java.util.ArrayList;
import java.util.List;

public class Department {

	private String departmentName;
	private List<Employee> employees;

	public Department(String departmentName) {
		this.departmentName = departmentName;
		this.employees = new ArrayList<>();
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public List<Employee> getEmployees() {
		return new ArrayList<>(employees);
	}

	public void addEmployee(Employee employee) {
		employees.add(employee);
	}

}
