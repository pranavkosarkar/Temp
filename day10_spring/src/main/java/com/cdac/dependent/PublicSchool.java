package com.cdac.dependent;

import com.cdac.dependency.EnglishTeacher;
import com.cdac.dependency.MathsTeacher;
import com.cdac.dependency.Teacher;

public class PublicSchool implements School {	
	//dependency
	private Teacher subjectTeacher;//=new MathsTeacher();
	
	//constructor based D.I
	public PublicSchool(Teacher myTeacher) {
		System.out.println("In constructor - " + getClass());	
		this.subjectTeacher=myTeacher;
	}
	//B.L
	@Override
	public void manageAcademics() {
		System.out.println("Managing academics here -");
		subjectTeacher.teach();//using dependency
	}	

}
