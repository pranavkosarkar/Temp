package com.healthcare.tester;

import static com.healthcare.utils.HibernateUtils.getSessionFactory;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.healthcare.dao.DoctorDao;
import com.healthcare.dao.DoctorDaoImpl;
import com.healthcare.entities.Doctor;

public class GetDoctorDetailsByJoinFetch {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in); SessionFactory sf = getSessionFactory()) {
			// Create dao instance
			DoctorDao docDao = new DoctorDaoImpl();
			System.out.println("Enter doctor id to get doctor details");
			// invoke dao's method
			Doctor doc = docDao.getDoctorDetailsByIdByJoinFetch(sc.nextLong());
			System.out.println("Doctor specific details "+doc);
			//get user details 
	//		User userDoc=doc.getUserDetails();
	//		System.out.println("Common details - "+userDoc);//one-one : eagerly loaded
			//get doc's appointments
			System.out.println("All appointments - ");
			doc.getAppointments().forEach(System.out::println);//LazyInitExc - one -> many : lazy -> appointments : PROXY
		} // JVM -> sf.close() -> DBCP auto cleaned up !

	}

}
