package com.cdac.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
/*
 * Optional class level annotation to specify - base URL pattern
 */
@RequestMapping("/test")
public class TestController {
	public TestController() {
		System.out.println("in ctor " + getClass());
	}

	/*
	 * Desc - Render results 
	 * URL - http://host:port/ctx/test/test1 , method GET Resp
	 * - ModelAndView Key - GET /test/test1 Value - TestController.testModelAndView
	 */
	@GetMapping("/test1")
	public ModelAndView testModelAndView() {
		System.out.println("in test m & v");
		/*
		 * public class ModelAndView(String lvn, String modelAttrName, Object modelAttrValu
		 */
		return new ModelAndView("test/display", "server_ts", LocalDateTime.now());
	}
	/*
	 * Handler rets ModelAndView -> D.S
	 * D.S -> sends LVN -> V.R -> AVN - /WEB-INF/views/test/display.jsp
	 * -> D.S -> adds model attribute under request scope -> forwards -> JSP
	 * JSP -> ${requestScope.attrName}
	 */
	
	/*
	 * Desc - Render results via Model Map
	 * URL - http://host:port/ctx/test/test2 , method GET Resp
	 * - LVN (Model Map)
	 *  Key - GET /test/test2 
	 *  Value - TestController.testModelMap
	 */
	@GetMapping("/test2")
	public String testModelMap(Model modelAttrMap) {
		System.out.println("in test model map "+modelAttrMap);//{}
		//add model attributes
		modelAttrMap.addAttribute("server_date", LocalDate.now())
		.addAttribute("server_time", LocalTime.now());
		return "test/display2";
	}
	/*
	 * Handler rets explicitly LVN + implcitly Model map -> D.S
	 * D.S sends LVN -> V.R -> AVN -> D.S
	 * D.S adds model attributes under request scope 
	 * -> forward to JSP
	 * -> ${....}
	 */
	

}
