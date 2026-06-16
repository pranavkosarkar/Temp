package com.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
//add a dervied query method - get available emps by dept id
	// select e from Employee e where e.myDepartment.id=:id and e.status=true
	List<Employee> findByMyDepartmentIdAndStatusTrue(Long departmentId);
}
