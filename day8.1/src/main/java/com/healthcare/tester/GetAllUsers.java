package com.healthcare.tester;

import static com.healthcare.utils.HibernateUtils.getSessionFactory;

import org.hibernate.SessionFactory;

import com.healthcare.dao.UserDao;
import com.healthcare.dao.UserDaoImpl;

public class GetAllUsers {

	public static void main(String[] args) {
		try (SessionFactory sf = getSessionFactory()) {
			//Create dao instance
			UserDao userDao=new UserDaoImpl();			
				//invoke dao's method
			userDao.getAllUsers()
			.forEach(System.out::println);
		} // JVM -> sf.close() -> DBCP auto cleaned up !

	}

}
