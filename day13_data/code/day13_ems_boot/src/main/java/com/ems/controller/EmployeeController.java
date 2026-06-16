package com.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ems.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	//depcy 
	@Autowired
	private EmployeeService employeeService;
/*
 * URL - http://localhost:8080/ems/employees/list
 * Method - POST
 * Payload - deptId=....
 * Resp - list of emps from specified dept by id
 */
	@PostMapping("/list")
	public String renderEmpListByDept(Model map,@RequestParam Long deptId)
	{
		System.out.println("in list emps "+map+" "+deptId);
		//invoke emp service's method
		map.addAttribute("emp_list", employeeService.getAllEmpsByDepartmentId(deptId));
		return "emps/list"; //  /WEB-INF/views/emps/list.jsp
		
	}
}
