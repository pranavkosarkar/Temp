package com.healthcare.tester;

import static com.healthcare.utils.HibernateUtils.getSessionFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.healthcare.dao.UserDao;
import com.healthcare.dao.UserDaoImpl;
import com.healthcare.entities.UserRole;

public class GetSelectedDetailsByRole {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
				SessionFactory sf = getSessionFactory()) {
			//Create dao instance
			UserDao userDao=new UserDaoImpl();
			System.out.println("Enter user role");
				//invoke dao's method
			userDao.getSelectedDetailsByRole(UserRole.valueOf(sc.next().toUpperCase())).forEach(System.out::println);
		} // JVM -> sf.close() -> DBCP auto cleaned up !

	}

}
