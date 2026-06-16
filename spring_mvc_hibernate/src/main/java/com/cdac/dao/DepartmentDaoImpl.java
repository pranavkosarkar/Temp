package com.cdac.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cdac.entities.Department;

@Repository //spring bean - DAL
public class DepartmentDaoImpl implements DepartmentDao {
	//dependency 
	@Autowired //mathcing by data type 
	private SessionFactory sessionFactory;

	@Override
	public List<Department> getAllDepartments() {
		String jpql="select d from Department d";
		return sessionFactory.getCurrentSession()
				.createQuery(jpql, Department.class)
				.getResultList();
	}

}
