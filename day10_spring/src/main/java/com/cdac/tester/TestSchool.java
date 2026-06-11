package com.cdac.tester;

import com.cdac.dependency.ScienceTeacher;
import com.cdac.dependent.PublicSchool;
import com.cdac.dependent.School;

public class TestSchool {

	public static void main(String[] args) {
		School school=new PublicSchool(new ScienceTeacher());
		school.manageAcademics();

	}

}
