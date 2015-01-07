package service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.MBookmark;
import model.MTag;
import service.IServiceHelloWorld;

public class ServiceHelloworld implements IServiceHelloWorld {

	public String getHtml() {
		return "Hello, ma couille!";
	}
	
	public MBookmark saveBookmark(final MBookmark newInstance) {
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
	
	public MTag saveTag(final MTag newInstance) {
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
	
	public List<MBookmark> getAllBookmarks() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp");
		EntityManager em = emf.createEntityManager();
		
		List<MBookmark> list = em.createQuery("from MBookmark").getResultList();
		em.close();
		emf.close();
		return list;
	}
	
	public MBookmark getBookmarkById(int bookmark_id){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp");
		EntityManager em = emf.createEntityManager();
		
		MBookmark bookmark = (MBookmark) em.createQuery("select c from MBookmark c where id = :id")
				.setParameter("id", bookmark_id)
				.getSingleResult();
		em.close();
		emf.close();
		return bookmark;
	}
	
	public List<MTag> getAllTags() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp");
		EntityManager em = emf.createEntityManager();
		
		List<MTag> list = em.createQuery("from MTag").getResultList();
		em.close();
		emf.close();
		return list;
	}
	
	public MTag getTagById(int id){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp");
		EntityManager em = emf.createEntityManager();
		
		MTag tag = (MTag) em.createQuery("select c from MTag c where id = :id")
				.setParameter("id", id)
				.getSingleResult();
		em.close();
		emf.close();
		return tag;
	}
	
	/* * remove tag for specified id */
	public void removeTagById(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp");
		EntityManager em = emf.createEntityManager();
		MTag tag = em.find(MTag.class, id);
		em.getTransaction().begin();
		em.remove(tag);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	/* * remove bookmark for specified id */
	public void removeBookmarkById(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp");
		EntityManager em = emf.createEntityManager();
		MBookmark bookmark = em.find(MBookmark.class, id);
		em.getTransaction().begin();
		em.remove(bookmark);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	/*
	* Add tag for the specified bookmark id
	*/

	public void addTagToBookmark(int id,String tagName){
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("tp");
	EntityManager em = emf.createEntityManager();
	MBookmark bookmark = em.find(MBookmark.class, id);
	em.getTransaction().begin();
	bookmark.addTag(new MTag(tagName));
	em.getTransaction().commit();
	em.close();
	emf.close();

	}
	
}
