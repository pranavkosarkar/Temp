package com.cdac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //to declare a spring bean containing req handling logic
//singleton & eager
public class HelloWorldController {
	public HelloWorldController() {
		System.out.println("in ctor "+getClass());
	}
	/*
	 * Request Handling method
	 * URL - http://host:port/ctx_path/
	 * SC populates the entry in HandlerMapping bean
	 * Key - GET /
	 * Value - com.cdac.controller.HelloWorldController.renderIndexPage
	 */
	@GetMapping("/") //intercepts GET request => doGet
	public String renderIndexPage() {
		System.out.println("in render index page");
		return "index"; //Logical (forward) view name -> AVN 
		// /WEB-INF/views/index.jsp
	}

}
