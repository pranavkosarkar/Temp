package com.cdac.dependent;

import com.cdac.dependency.Coach;
import com.cdac.dependency.Teacher;

public class PublicSchool implements School {
	// dependency - mandatory
	private Teacher subjectTeacher;// =new MathsTeacher();
	
	// coach - optional depcy
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
	public void anyInit() {
		System.out.println("in init");
	}

	// destroy method
	public void anyDestroy() {
		System.out.println("in destroy");
	}

	//setters
	public void setSubjectTeacher(Teacher subjectTeacher) {
		this.subjectTeacher = subjectTeacher;
		System.out.println("in setter - teacher");
	}

	public void setSportsCoach(Coach sportsCoach) {
		this.sportsCoach = sportsCoach;
		System.out.println("in setter - coach");
	}

	
	

}
