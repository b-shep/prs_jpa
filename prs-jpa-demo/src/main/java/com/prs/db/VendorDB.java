package com.prs.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.prs.logic.User;
import com.prs.logic.Vendor;

public class VendorDB extends Database<Vendor>{
	
	List<Vendor> vendors = null;
	
	@Override
	public List<Vendor> getAll(){
		
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		
		try {
			Query q = em.createQuery("Select v from Vendor v");
			vendors = q.getResultList();
		} finally {
			em.close();
		}
		return vendors;
	}
	
	public static Vendor get(int id) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String queryString = "SELECT v FROM Vendor v " +
							 "WHERE v.id = :id";
		TypedQuery<Vendor> q = em.createQuery(queryString, Vendor.class);
		q.setParameter("id", id);
		Vendor vendor = null;
		try {
			vendor = q.getSingleResult();
		}
		catch (NoResultException e) {
			System.err.println(e);
		}
		finally {
			em.close();
		}
		return vendor;
		
	}
	
	public boolean add(Vendor v) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			em.persist(v);
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
	
	public static boolean delete(Vendor v) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			em.remove(em.merge(v));
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
	
	public boolean update(Vendor v) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			em.merge(v);
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
		String vendorString = "";
		for (Vendor v: vendors) {
			vendorString += v.toString();
		}
		return vendorString;
		
		
	}
	
	

}
