package com.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
//add a dervied query method - get emps by dept id
	// select e from Employee e where e.myDepartment.id=:id
	List<Employee> findByMyDepartmentId(Long departmentId);
}
