package com.healthcare.tester;

import static com.healthcare.utils.HibernateUtils.getSessionFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.healthcare.dao.UserDao;
import com.healthcare.dao.UserDaoImpl;
import com.healthcare.entities.UserRole;

public class ApplyDiscountByRole {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in); SessionFactory sf = getSessionFactory()) {
			// Create dao instance
			UserDao userDao = new UserDaoImpl();
			System.out.println("Enter user role and discount");
			// invoke dao's method
			System.out.println(userDao.applyDiscountByRole(UserRole.valueOf(sc.next().toUpperCase()),sc.nextInt()));
		} // JVM -> sf.close() -> DBCP auto cleaned up !

	}

}
