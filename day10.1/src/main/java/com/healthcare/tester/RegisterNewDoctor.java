package com.healthcare.tester;

import static com.healthcare.utils.HibernateUtils.getSessionFactory;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.healthcare.dao.DoctorDao;
import com.healthcare.dao.DoctorDaoImpl;
import com.healthcare.entities.Doctor;
import com.healthcare.entities.User;
import com.healthcare.entities.UserRole;

public class RegisterNewDoctor {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
				SessionFactory sf = getSessionFactory()) {
			//Create dao instance
			DoctorDao docDao=new DoctorDaoImpl();
			System.out.println("Enter user details - firstName,  lastName,  email,  password,  dob,  phone,  regAmount");
			//Create transient user (not yet persistent !)
			User user=new User(sc.next(), sc.next(), sc.next(), sc.next(), LocalDate.parse(sc.next()), sc.next(), UserRole.ROLE_DOCTOR, sc.nextInt());
			System.out.println("Enter Doctor specific details - qualifications,  experienceInYears,  fees,  appointment Time in minutes ,  speciality");
			Doctor doctor=new Doctor(sc.next(),sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.next());
			//establish Doctor 1--->1 User association
			doctor.setUserDetails(user);
			
			//invoke dao's method
			System.out.println("Reg status "+docDao.registerDoctor(doctor));
		} // JVM -> sf.close() -> DBCP auto cleaned up !

	}

}
