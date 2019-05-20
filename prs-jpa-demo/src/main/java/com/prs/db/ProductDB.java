package com.prs.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.prs.logic.Product;
import com.prs.logic.User;
import com.prs.logic.Vendor;

public class ProductDB extends Database<Product>{

	List<Product> products = null;
	
	public List<Product> getAll(){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		
		try {
			Query q = em.createQuery("Select p from Product p");
			products = q.getResultList();
		} finally {
			em.close();
		}
		return products;
	}
	
	public static Product get(int id) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String queryString = "SELECT v FROM Product v " +
							 "WHERE v.id = :id";
		TypedQuery<Product> q = em.createQuery(queryString, Product.class);
		q.setParameter("id", id);
		Product product = null;
		try {
			product = q.getSingleResult();
		}
		catch (NoResultException e) {
			System.err.println(e);
		}
		finally {
			em.close();
		}
		return product;
		
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
	
	public static boolean delete(Product p) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			em.remove(em.merge(p));
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
	
	public boolean update(Product p) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		try {
			trans.begin();
			em.merge(p);
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
		String productString = "";
		for (Product p: products) {
			productString += p.toString();
		}
		return productString;
	}

}
