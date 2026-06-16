package com.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ems.custom_exceptions.ResourceNotFoundException;
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
		return empRepo.findByMyDepartmentIdAndStatusTrue(deptId);
	}

	@Override
	@Transactional//readOnly=false
	public String deleteEmpDetails(Long empId) {
		// 1. get emp by its id
		Employee emp=empRepo.findById(empId) //Optional<Emp>
				.orElseThrow(() -> new ResourceNotFoundException("Invalid emp id !!!!!"));
		//=> valid id => emp : PERSISTENT
		//setter
		emp.setStatus(false);//changing the state of persistent entity(dirty)
		return "Soft deleted emp details .....";
	}
	/*
	 * when @Transactional method rets
	 *  - Tx mgr checks for un checked exc
	 *   - no exc => tx.commit() -> session.flush-> dirty checking
	 *   -> DML : update
	 *   session.close
	 *   ->  db cn rets to Hikari CP
	 *   -> L1 cache destroyed 
	 * 
	 */

	@Override
	public Employee getEmpDetails(Long empId) {
		
		return empRepo.findById(empId)
				.orElseThrow(() -> 
				new ResourceNotFoundException("Emp id invalid !!!!"));
	}

	@Override
	@Transactional
	public String updateEmpDetails(Long empId, Employee emp) {
		// 1. get emp details by id
		Employee existingEmp=getEmpDetails(empId);
		//existingEmp - persistent
		//2. update state - setters
		existingEmp.setPassword(emp.getPassword());
		existingEmp.setSalary(emp.getSalary());
		existingEmp.setEmpType(emp.getEmpType());
		existingEmp.setJoinDate(emp.getJoinDate());
		return "Updated emp details....";
	}
	
	
	
	
	
	

}
