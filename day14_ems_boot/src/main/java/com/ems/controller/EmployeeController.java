package com.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ems.entities.Employee;
import com.ems.service.EmployeeService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/employees") //base URL pattern
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
	@RequestMapping("/list")
	public String renderEmpListByDept(Model map,@RequestParam Long deptId,HttpSession session)
	{
		//Java - Long deptId=Long.parseLong(request.getParamter("deptId"));
		System.out.println("in list emps "+map+" "+deptId);
		//add dept id under session scope
		session.setAttribute("dept_id", deptId);
		//invoke emp service's method
		map.addAttribute("emp_list", employeeService.getAllEmpsByDepartmentId(deptId));
		return "emps/list"; //  /WEB-INF/views/emps/list.jsp
		
	}
	/*
	 * URL - http://localhost:8080/ems/employees/delete?empId=${emp.id}
	 * Method - GET (clicking on the link)
	 * Query string - empId
	 * Resp - After soft deleting emp details , redirect the client to emp list
	 */
	@GetMapping("/delete")
	public String softDeleteEmpDetails(@RequestParam Long empId,Model map,HttpSession session)
	{
		System.out.println("in del emp "+empId);
		//invoke service layer method
		map.addAttribute("message",employeeService.deleteEmpDetails(empId));
		return "redirect:/employees/list?deptId="+session.getAttribute("dept_id");
	}
	/*
	 * Java - response.sendRedirect(response.encodeRedirectURL("/employees/list"));
	 * WC -> sends temp redirect resp
	 * SC 302 , header - Location -/employees/list  , body -empty
	 * -> web browser
	 * -> sends next redirect request
	 * URL - ...../ems/employees/list 
	 *  Method -GET
	 * 
	 * 
	 * 
	 */
	/*
	 * Desc - Get emp details by its id
	 * URL - http://localhost:8080/ems/employees/update?empId=7
	 * Method - GET
	 * Resp - LVN -> AVN -> emp details(model) rendered to form(view)
	 */
	 @GetMapping("/update")
	 public String renderUpdateForm(@RequestParam Long empId,Model map)
	 {
		 System.out.println("in render update form "+empId);
		 map.addAttribute("emp_details", employeeService.getEmpDetails(empId));
		 return "emps/edit";
	 }
	 /*
		 * Desc - Update emp details by its id
		 * URL - http://localhost:8080/ems/employees/update?empId=7
		 * Method - POST
		 * Resp - redirect the client to emp list with updated view
		 */
		 @PostMapping("/update")
		 public String processUpdateForm(@RequestParam Long empId,@ModelAttribute("emp_details") Employee emp,HttpSession session)
		 {
			 System.out.println("in process  update form "+empId+" "+emp);
			 //invoke service layer method
			System.out.println(employeeService.updateEmpDetails(empId,emp));
			 return "redirect:/employees/list?deptId="+session.getAttribute("dept_id");
		 }
	 
	
}
