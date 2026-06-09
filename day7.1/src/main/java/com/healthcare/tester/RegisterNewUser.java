package com.healthcare.tester;

import static com.healthcare.utils.HibernateUtils.getSessionFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.healthcare.dao.UserDao;
import com.healthcare.dao.UserDaoImpl;
import com.healthcare.entities.User;
import com.healthcare.entities.UserRole;

public class RegisterNewUser {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
				SessionFactory sf = getSessionFactory()) {
			//Create dao instance
			UserDao userDao=new UserDaoImpl();
			System.out.println("Enter user details - firstName,  lastName,  email,  password,  dob,  phone,role,  regAmount");
			//Create transient user (not yet persistent !)
			User user=new User(sc.next(), sc.next(), sc.next(), sc.next(), LocalDate.parse(sc.next()), sc.next(), UserRole.valueOf(sc.next().toUpperCase()), sc.nextInt());
			//invoke dao's method
			System.out.println("Reg status "+userDao.registerUser(user));
		} // JVM -> sf.close() -> DBCP auto cleaned up !

	}

}
