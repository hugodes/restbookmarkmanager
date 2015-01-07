package service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.MBookmark;
import service.IServiceHelloWorld;

public class ServiceHelloworld implements IServiceHelloWorld {

	public String getHtml() {
		return "Hello, ma couille!";
	}
	
	public MBookmark save(final MBookmark newInstance) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(newInstance);
		em.flush();
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		return newInstance;
	}
	
	public List<MBookmark> getAll() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp");
		EntityManager em = emf.createEntityManager();
		
		List<MBookmark> list = em.createQuery("from MBookmark").getResultList();
		em.close();
		emf.close();
		return list;
	}
	
	public MBookmark getById(int id){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp");
		EntityManager em = emf.createEntityManager();
		
		MBookmark bookmark = (MBookmark) em.createQuery("select c from MBookmark c where id = :id")
				.setParameter("id", id)
				.getSingleResult();
		em.close();
		emf.close();
		return bookmark;
	}
	
}
