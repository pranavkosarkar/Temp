package com.cdac.dependent;

import com.cdac.dependency.Coach;
import com.cdac.dependency.Teacher;

public class PublicSchool implements School {
	// dependency - mandatory
	private Teacher subjectTeacher;// =new MathsTeacher();
	// coach - optional depcy
	private Coach sportsCoach;

	public PublicSchool(Teacher subjectTeacher, Coach sportsCoach) {
		System.out.println("in ctor of " + getClass());
		this.subjectTeacher = subjectTeacher;
		this.sportsCoach = sportsCoach;
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

}
