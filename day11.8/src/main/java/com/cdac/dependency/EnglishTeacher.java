package com.cdac.dependency;

import org.springframework.stereotype.Component;

//singleton & eager spring bean

@Component("eng") //<bean id="eng" class="...."/>
public class EnglishTeacher implements Teacher {
	public EnglishTeacher() {
		System.out.println("In constructor - " + getClass());
	}

	@Override
	public void teach() {
		System.out.println("Practice English Grammar");
	}

}
