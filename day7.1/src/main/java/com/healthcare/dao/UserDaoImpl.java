package com.healthcare.dao;

import org.hibernate.*;

import com.healthcare.entities.User;
import static com.healthcare.utils.HibernateUtils.getSessionFactory;

public class UserDaoImpl implements UserDao {

	@Override
	public String registerUser(User user) {
		//user - TRANSIENT
		String mesg = "User registration failed....";
		/*
		 * 1. Get Session from SessionFactory Method of SessionFactory 1.1 public
		 * Session getCurrentSession() throws HibernateException OR 1.2 public Session
		 * openSession() throws HibernateException
		 */
		Session session = getSessionFactory().getCurrentSession();
		/*
		 * 2. Begin Transaction Session API Transaction beginTransaction() throws
		 * HibernateException
		 * 
		 */
		Transaction tx = session.beginTransaction();
		Session session2 = getSessionFactory().getCurrentSession();
		System.out.println(session == session2);// t
		System.out.println("is connected to db " + session.isConnected());// t
		try {
			/*
			 * Session API to make transient -> persistent (insert a rec) public void
			 * persist(Object o) throws HibernateException
			 */
			session.persist(user);
			//user - PERSISTENT (exists in L1 , db rec will be inserted upon commit)
			tx.commit();
			//user - DETACHED (not in L1 cache , exists in DB)
			mesg = "User registered successfully , ID " + user.getUserId();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			// re throw same exception to the caller - so that caller knows about it
			throw e;
		}
		return mesg;
	}

	@Override
	public User getUserDetailsById(Long userId) {
		User user = null; //does not exist - heap
		// 1. Get Session from SessionFactory
		Session session = getSessionFactory().getCurrentSession();
		// 2. Begin Transaction
		Transaction tx = session.beginTransaction();
		try {
			
			user = session.find(User.class, userId);//select
			/*
			 * select -> ResultSet -> User
			 * user - in case of valid id - PERSISTENT (loaded from db, exists in L1 cache)
			 */
			user = session.find(User.class, userId);//cache
			user = session.find(User.class, userId);
			user = session.find(User.class, userId);
			user = session.find(User.class, userId);
		//	tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			// re throw the same exception to the caller
			throw e;
		}
		//user -  DETACHED
		return user;
	}

}
