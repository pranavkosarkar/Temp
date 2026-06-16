package com.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ems.entities.Employee;
import com.ems.repository.EmployeeRepository;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {
	//depcy
	@Autowired
	private EmployeeRepository empRepo;

	@Override
	public List<Employee> getAllEmpsByDepartmentId(Long deptId) {
		// TODO Auto-generated method stub
		return empRepo.findByMyDepartmentId(deptId);
	}

}
