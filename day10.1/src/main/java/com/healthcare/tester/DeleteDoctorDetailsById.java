package com.healthcare.tester;

import static com.healthcare.utils.HibernateUtils.getSessionFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.healthcare.dao.DoctorDao;
import com.healthcare.dao.DoctorDaoImpl;

public class DeleteDoctorDetailsById {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
				SessionFactory sf = getSessionFactory()) {
			//Create dao instance
			DoctorDao docDao=new DoctorDaoImpl();
			System.out.println("Enter doctor id to hard delete COMPLETE doctor details");
				//invoke dao's method
			System.out.println(docDao.deleteDoctorDetailsById(sc.nextLong()));
		} // JVM -> sf.close() -> DBCP auto cleaned up !

	}

}
