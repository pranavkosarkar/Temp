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

}
