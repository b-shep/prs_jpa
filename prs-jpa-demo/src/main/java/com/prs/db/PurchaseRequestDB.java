package com.prs.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.prs.logic.PurchaseRequest;
import com.prs.logic.User;
import com.prs.logic.Vendor;

public class PurchaseRequestDB extends Database<PurchaseRequest>{

	List<PurchaseRequest> purchaseRequests = null;
	
	public List<PurchaseRequest> getAll(){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		
		try {
			Query q = em.createQuery("Select pr from PurchaseRequest pr");
			purchaseRequests = q.getResultList();
		} finally {
			em.close();
		}
		return purchaseRequests;
	}
	
	public static PurchaseRequest get(int id) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String queryString = "SELECT pr FROM PurchaseRequest pr " +
							 "WHERE pr.id = :id";
		TypedQuery<PurchaseRequest> q = em.createQuery(queryString, PurchaseRequest.class);
		q.setParameter("id", id);
		PurchaseRequest purchaseRequest = null;
		try {
			purchaseRequest = q.getSingleResult();
		}
		catch (NoResultException e) {
			System.err.println(e);
		}
		finally {
			em.close();
		}
		return purchaseRequest;
		
	}
	
	public boolean add(PurchaseRequest pr) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			em.persist(pr);
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
	
	public static boolean delete(PurchaseRequest pr) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			em.remove(em.merge(pr));
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
	
	public boolean update(PurchaseRequest pr) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			em.merge(pr);
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
		String purchaseRequestString = "";
		for (PurchaseRequest p: purchaseRequests) {
			purchaseRequestString += p.toString();
		}
		return purchaseRequestString;
	}

}
