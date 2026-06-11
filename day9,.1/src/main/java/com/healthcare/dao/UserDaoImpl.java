package com.healthcare.dao;

import org.hibernate.*;

import com.healthcare.dtos.UserDTO;
import com.healthcare.entities.User;
import com.healthcare.entities.UserRole;

import static com.healthcare.utils.HibernateUtils.getSessionFactory;

import java.time.LocalDate;
import java.util.List;

public class UserDaoImpl implements UserDao {

	@Override
	public String registerUser(User user) {
		// user - TRANSIENT
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
			// user - PERSISTENT (exists in L1 , db rec will be inserted upon commit)
			tx.commit();
			// user - DETACHED (not in L1 cache , exists in DB)
			mesg = "User registered successfully , ID " + user.getId();
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
		User user = null; // does not exist - heap
		// 1. Get Session from SessionFactory
		Session session = getSessionFactory().getCurrentSession();
		// 2. Begin Transaction
		Transaction tx = session.beginTransaction();
		try {

			user = session.find(User.class, userId);// select
			/*
			 * select -> ResultSet -> User user - in case of valid id - PERSISTENT (loaded
			 * from db, exists in L1 cache)
			 */
			user = session.find(User.class, userId);// cache
			user = session.find(User.class, userId);
			user = session.find(User.class, userId);
			user = session.find(User.class, userId);
			// tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			// re throw the same exception to the caller
			throw e;
		}
		// user - DETACHED
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = null;
		String jpql = "select u from User u";
		// 1. Get Session from SessionFactory
		Session session = getSessionFactory().getCurrentSession();
		// 2. Begin Tx
		Transaction tx = session.beginTransaction();
		try {
			users = session.createQuery(jpql, User.class).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			// re throw the same exception to the caller
			throw e;
		}
		return users;
	}

	@Override
	public List<User> getUsersByDateAndRole(UserRole role1, LocalDate date1) {
		List<User> users = null;
		String jpql = "select u from User u where u.role=:rl and u.dob > :dt";
		// 1. Get Session from SessionFactory
		Session session = getSessionFactory().getCurrentSession();
		// 2. Begin Tx
		Transaction tx = session.beginTransaction();
		try {
			users = session.createQuery(jpql, User.class).setParameter("rl", role1).setParameter("dt", date1)
					.getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			// re throw the same exception to the caller
			throw e;
		}
		return users;
	}

	@Override
	public User authenticateUser(String email1, String password1) {
		User user = null;
		String jpql = "select u from User u where u.email=:em and u.password=:pass";
		// 1. Get Session from SessionFactory
		Session session = getSessionFactory().getCurrentSession();
		// 2. Begin Tx
		Transaction tx = session.beginTransaction();
		try {
			user = session.createQuery(jpql, User.class).setParameter("em", email1).setParameter("pass", password1)
					.getSingleResult();// select
			// user - valid user - PERSISTENT(exists in DB & lifted in cache)
			user.setRegAmount(user.getRegAmount() + 100);// changing state of the persistent entity
			user.setPhone("9645246256");
			// session.evict(user);
			tx.commit();
			/*
			 * session.flush() -> auto dirty checking -> updated state -> DML - update ->
			 * session.close() -> L1 cache is destroyed & dn cn rets to DBCP
			 */
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			// re throw the same exception to the caller
			throw e;
		}
		// user - DETACHED (from L1)
		user.setRegAmount(user.getRegAmount() + 100);
		user.setPhone("9999999999");
		return user;
	}

	@Override
	public List<String> getUsersLastNamesByRole(UserRole role1) {
		List<String> lastNames = null;
		String jpql = "select u.lastName from User u where u.role=:rl";
		// 1. Get Session from SessionFactory
		Session session = getSessionFactory().getCurrentSession();
		// 2. Begin Tx
		Transaction tx = session.beginTransaction();
		try {
			lastNames = session.createQuery(jpql, String.class).setParameter("rl", role1).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			// re throw the same exception to the caller
			throw e;
		}
		return lastNames;
	}

	@Override
	public List<UserDTO> getSelectedDetailsByRole(UserRole userRole) {
		List<UserDTO> dtos = null;
		String jpql = "select new com.healthcare.dtos.UserDTO(u.firstName,u.lastName,u.dob) from User u where u.role=:role";
		// 1. Get Session from SessionFactory
		Session session = getSessionFactory().getCurrentSession();
		// 2. Begin Tx
		Transaction tx = session.beginTransaction();
		try {
			dtos = session.createQuery(jpql, UserDTO.class).setParameter("role", userRole).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			// re throw the same exception to the caller
			throw e;
		}
		return dtos;
	}

//for bulk updations
	@Override
	public String applyDiscountByRole(UserRole userRole1,int discount) {
		String mesg="Updation failed.....";
		String jpql="update User u set u.regAmount=u.regAmount-:disc where u.role=:rl";
		// 1. Get Session from SessionFactory
		Session session = getSessionFactory().getCurrentSession();
		// 2. Begin Tx
		Transaction tx = session.beginTransaction();
		try {
			int updateCount=session.createMutationQuery(jpql)
					.setParameter("disc", discount)
					.setParameter("rl",userRole1 )
					.executeUpdate();
			tx.commit();
			mesg="Updated "+updateCount+" no of users ";
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
	public String deleteUserDetailsById(Long userId) {
		String mesg="Deletion failed!!!!!!!!!!";
		User user=null;
		// 1. Get Session from SessionFactory
		Session session=getSessionFactory().getCurrentSession();
		//2. Begin Tx
		Transaction tx=session.beginTransaction();
		try {
			//3 Find user details by id
			user=session.find(User.class, userId);
			//4. check for null
			if(user != null)
			{
				//user - persistent
				session.remove(user);
				//user - REMOVED (marked for removal)
				mesg="User details will be deleted upon commit....";
			}
			tx.commit();
			/*
			 * session.flush() -> auto dirty checking -> DML - delete
			 * session.close() -> L1 destroyed -> cn rets DBCP
			 */
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			// re throw the same exception to the caller
			throw e;
		}
		//user - TRANSIENT
		return mesg;
	}
	//user - GC -> does not exist	

}
