package com.cdac.tester;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cdac.dependent.PublicSchool;

public class TestSpringContainer {

	public static void main(String[] args) {
		try (ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("bean-config.xml")) {
			System.out.println("SC up & running.....");
			//B.L - manage academics - public school instance
			PublicSchool school1=ctx.getBean("pub_school", PublicSchool.class);
			PublicSchool school2=ctx.getBean("pub_school", PublicSchool.class);
			System.out.println(school1==school2);//t
			//B.L
			school1.manageAcademics();				
		} // JVM - ctx.close() => SC shuts down => destroy method of singleton bean => GC
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
