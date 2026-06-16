package com.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ems.entities.Department;
import com.ems.repository.DepartmentRepository;

@Service //to declare a spring bean containing B.L
@Transactional(readOnly = true) //to manage the transactions - automatically 
public class DepartmentServiceImpl implements DepartmentService {
	//dependency
	@Autowired
	private DepartmentRepository departmentDao;

	@Override
	public List<Department> getAllDepartments() {
		// TODO Auto-generated method stub
		return departmentDao.findAll();
	}

}
