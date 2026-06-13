package com.cdac.dependent;

import com.cdac.dependency.Coach;
import com.cdac.dependency.Teacher;

public class PublicSchool implements School {
	// dependency - mandatory
	private Teacher subjectTeacher;// =new MathsTeacher();
	// funds - long - mandatory
	private long funds;
	// coach - optional depcy
	private Coach sportsCoach;

	// constructor based D.I
	public PublicSchool(long schoolFunds, Teacher myTeacher) {
		System.out.println("In constructor - " + getClass());
		this.funds=schoolFunds;
		this.subjectTeacher = myTeacher;
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
	//setter based D.I
	public void setSportsCoach(Coach sportsCoach) {
		this.sportsCoach = sportsCoach;
		System.out.println("in setter - coach");
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
