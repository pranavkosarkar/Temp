package com.cdac.dependent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.cdac.dependency.Coach;
import com.cdac.dependency.Teacher;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
//singleton & eager , maths teacher & football coach
@Component("pub_school")
public class PublicSchool implements School {
	// dependency - Field Level D.I
	@Autowired //auto wiring - SC tries to match by data type of the field
	@Qualifier("maths")
	private Teacher subjectTeacher;// =new MathsTeacher();
	
	// coach - optional depcy
	@Autowired(required = false)
	@Qualifier("footballCoach123")
	private Coach sportsCoach;

	public  PublicSchool() {
		System.out.println("In constructor - " + getClass());		
	}

	// B.L
	@Override
	public void manageAcademics() {
		System.out.println("Managing academics here -");
		subjectTeacher.teach();// using dependency - mandatory
	}

	@Override
	public void organizeSportsEvent() {
		System.out.println("Preparing for sports event");
		System.out.println(sportsCoach.getDailyWorkout());// using optional dependency

	}

	// init method
	@PostConstruct
	public void anyInit() {
		System.out.println("in init");
	}

	// destroy method
	@PreDestroy
	public void anyDestroy() {
		System.out.println("in destroy");
	}

	
	

}
