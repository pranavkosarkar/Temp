package com.cdac.dependent;

import com.cdac.dependency.Coach;
import com.cdac.dependency.Teacher;

public class PublicSchool implements School {
	// dependency - mandatory
	private Teacher[] subjectTeachers;// =new MathsTeacher();
	// coach - optional depcy
	private Coach[] sportsCoaches;

	public PublicSchool(Teacher[] subjectTeachers, Coach[] sportsCoaches) {
		System.out.println("in ctor of " + getClass());
		this.subjectTeachers = subjectTeachers;
		this.sportsCoaches = sportsCoaches;
	}

	// B.L
	@Override
	public void manageAcademics() {
		System.out.println("Managing academics here -");
		for (Teacher t : subjectTeachers)
			t.teach();// using dependency - mandatory
	}

	@Override
	public void organizeSportsEvent() {
		System.out.println("Preparing for sports event");
		for (Coach c : sportsCoaches)
			System.out.println(c.getDailyWorkout());

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
