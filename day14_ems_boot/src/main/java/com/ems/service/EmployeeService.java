package com.ems.service;

import java.util.List;

import com.ems.entities.Employee;

public interface EmployeeService {
	List<Employee> getAllEmpsByDepartmentId(Long deptId);

	String deleteEmpDetails(Long empId);

	Employee getEmpDetails(Long empId);

	String  updateEmpDetails(Long empId, Employee emp);
}
