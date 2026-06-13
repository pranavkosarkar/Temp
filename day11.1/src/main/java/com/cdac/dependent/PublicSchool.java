package com.cdac.dependent;

import com.cdac.dependency.Teacher;

public class PublicSchool implements School {
	// dependency - mandatory
	private Teacher subjectTeacher;// =new MathsTeacher();
	// funds - long - mandatory
	private long funds;	

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

	// destroy method
	public void anyDestroy() {
		System.out.println("in destroy");
	}
	
	// init method
		public void anyInit() {
			System.out.println("in init");
		}
}
