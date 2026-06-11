package com.healthcare.dao;

import static com.healthcare.utils.HibernateUtils.getSessionFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.healthcare.entities.Doctor;
public class DoctorDaoImpl implements DoctorDao {

	@Override
	public String registerDoctor(Doctor doctor) {
		String mesg="doc reg failed !!!!";
		// 1. Get Session from SessionFactory
		Session session=getSessionFactory().getCurrentSession();
		//2. Begin Tx
		Transaction tx=session.beginTransaction();
		try {
			//session.persist(doctor.getUserDetails());//rec inserted in users - parent
			session.persist(doctor);//rec inserted in dotors - child
			tx.commit();
			mesg="doctor reg successful";
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			// re throw the same exception to the caller
			throw e;
		}
		return mesg;
	}

	@Override
	public String deleteDoctorDetailsById(Long doctorId) {
		String mesg="Deletion Failed ";
		// 1. Get Session from SessionFactory
		Session session=getSessionFactory().getCurrentSession();
		//2. Begin Tx
		Transaction tx=session.beginTransaction();
		try {
			//3. get doc details by its id
			Doctor doctor=session.find(Doctor.class, doctorId);
			//4 chk for null -> then remove
			if(doctor != null)
			{
				//doctor : PERSISTENT 
				session.remove(doctor);
			//	session.remove(doctor.getUserDetails()); NOT needed explicitly - cascading !
				mesg="deleted complete details....";
			}
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			// re throw the same exception to the caller
			throw e;
		}
		return mesg;
	}

	@Override
	public Doctor getDoctorDetailsById(Long doctorId) {
		Doctor doctor=null;
		// 1. Get Session from SessionFactory
		Session session=getSessionFactory().getCurrentSession();
		//2. Begin Tx
		Transaction tx=session.beginTransaction();
		try {
			doctor=session.find(Doctor.class, doctorId);//select query is fored on doctors table & NOT on appointments table
			//doctor - persistent
			//simply access the size of the appointment list
			doctor.getAppointments().size();//Hint to JPA -> to fetch data from appointments table
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			// re throw the same exception to the caller
			throw e;
		}
		return doctor;//doctor - detached - session scope- over !!!!!
	}

	@Override
	public Doctor getDoctorDetailsByIdByJoinFetch(Long doctorId) {
		Doctor doc=null;
		String jpql="select d from Doctor d left join fetch d.appointments where d.id=:did";
		// 1. Get Session from SessionFactory
		Session session=getSessionFactory().getCurrentSession();
		//2. Begin Tx
		Transaction tx=session.beginTransaction();
		try {
			doc=session.createQuery(jpql, Doctor.class)
					.setParameter("did", doctorId)
					.getSingleResult();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			// re throw the same exception to the caller
			throw e;
		}
		return doc;
	}
	
	
	
	

}
