package com.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ems.service.DepartmentService;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

	// dependency
	@Autowired
	private DepartmentService departmentService;
	
	public DepartmentController() {
		System.out.println("in ctor "+getClass());
	}

	
	/*
	 * URL - ...../departments/list  method - GET
	 * Resp - LVN + list of departments
	 */
	 @GetMapping("/list")
	 public String renderDepartmentList(Model map)
	 {
		 System.out.println("in list depts "+map);//{}
		 map.addAttribute("dept_list", departmentService.getAllDepartments());
		 return "depts/list";
	 }
	 /*
	  * Handler rets LVN (MOdel Map) -> D.S
	  * -> sends LVN -> V.R -> AVN -> D.S
	  * -> D.S adds model attr under req scope -> forward JSP
	  */

}
