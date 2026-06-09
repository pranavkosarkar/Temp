package com.healthcare.utils;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static SessionFactory sessionFactory;
	static {
		System.out.println("in static block");
		/*
		 * 1. Create Configuration class instace
		 * 2. Configure it
		 * 3. Build SessionFactory
		 */
		sessionFactory=new Configuration() //empty config
				.configure() //loads all props from hibernate.cfg.xml
				.buildSessionFactory();// DBCP 
	}
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
}
