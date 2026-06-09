package com.healthcare.tester;

import static com.healthcare.utils.HibernateUtils.getSessionFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.healthcare.dao.UserDao;
import com.healthcare.dao.UserDaoImpl;
import com.healthcare.entities.User;
import com.healthcare.entities.UserRole;

public class GetUserDetailsById {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
				SessionFactory sf = getSessionFactory()) {
			//Create dao instance
			UserDao userDao=new UserDaoImpl();
			System.out.println("Enter user id");
				//invoke dao's method
			System.out.println(userDao.getUserDetailsById(sc.nextLong()));
		} // JVM -> sf.close() -> DBCP auto cleaned up !

	}

}
