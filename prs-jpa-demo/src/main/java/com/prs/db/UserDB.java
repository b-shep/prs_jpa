package com.prs.db;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.prs.logic.User;
import com.prs.logic.Vendor;

public class UserDB extends Database<User> {

	List<User> users = null;
	
	public List<User> getAll(){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			Query q = em.createQuery("Select u from User u");
			users = q.getResultList();
		} finally {
			em.close();
		}
		return users;
	}
	
	public static User get(int id) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String queryString = "SELECT u FROM User u " +
							 "WHERE u.id = :id";
		TypedQuery<User> q = em.createQuery(queryString, User.class);
		q.setParameter("id", id);
		User user = null;
		try {
			user = q.getSingleResult();
		}
		catch (NoResultException e) {
			System.err.println(e);
		}
		finally {
			em.close();
		}
		return user;
		
	}
	
	public boolean add(User u) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			em.persist(u);
			trans.commit();
			return true;
		} catch (Exception ex) {
			System.err.println(ex);
			trans.rollback();
			return false;
		} finally {
			em.close();
		}
	}
	
	public static boolean delete(User u) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			em.remove(em.merge(u));
			trans.commit();
			return true;
		} catch (Exception ex) {
			System.err.println(ex);
			trans.rollback();
			return false;
		} finally {
			em.close();
		}
	}
	
	public boolean update(User u) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			em.merge(u);
			trans.commit();
			return true;
		} catch (Exception ex) {
			System.err.println(ex);
			trans.rollback();
			return false;
		} finally {
			em.close();
		}
	}
	
	@Override
	public String toString() {
		String userString = "";
		for (User u: users) {
			userString += u.toString();
		}
		return userString;
	}
}
