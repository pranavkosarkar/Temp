package com.healthcare.tester;

import static com.healthcare.utils.HibernateUtils.getSessionFactory;

import org.hibernate.SessionFactory;

public class TestHibernate {

	public static void main(String[] args) {
		try (SessionFactory sf = getSessionFactory()) {
			System.out.println("Hibernate up & running ....");
		} // JVM -> sf.close() -> DBCP auto cleaned up !

	}

}
