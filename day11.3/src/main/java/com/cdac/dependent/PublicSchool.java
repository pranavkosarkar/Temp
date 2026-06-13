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

	private PublicSchool(long schoolFunds, Teacher myTeacher) {
		System.out.println("In constructor - " + getClass());
		this.funds = schoolFunds;
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

	// init method
	public void anyInit() {
		System.out.println("in init");
	}

	// destroy method
	public void anyDestroy() {
		System.out.println("in destroy");
	}

	// factory method
	public static PublicSchool myFactoryMethod(long funds1, Teacher teacher1, Coach coach1) {
		System.out.println("in factory method ");
		PublicSchool school=new PublicSchool(funds1, teacher1);
		school.sportsCoach=coach1;
		return school;
	}

}
