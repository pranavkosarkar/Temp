package com.cdac.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdac.dao.DepartmentDao;
import com.cdac.entities.Department;

@Service //to declare a spring bean containing B.L
@Transactional //to manage the transactions - automatically 
public class DepartmentServiceImpl implements DepartmentService {
	//dependency
	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public List<Department> getAllDepartments() {
		// TODO Auto-generated method stub
		return departmentDao.getAllDepartments();
	}

}
