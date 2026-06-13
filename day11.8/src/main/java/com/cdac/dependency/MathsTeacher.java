package com.cdac.dependency;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//prototype spring bean , id="maths"
@Component("maths")
@Scope("prototype")
public class MathsTeacher implements Teacher {
	public MathsTeacher() {
		System.out.println("In constructor - " + getClass());
	}

	@Override
	public void teach() {
		System.out.println("Know your tables well !");
	}

}
