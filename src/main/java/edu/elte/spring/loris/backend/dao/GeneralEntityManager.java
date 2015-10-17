package edu.elte.spring.loris.backend.dao;

import java.util.List;

import javax.persistence.EntityManager;

public interface GeneralEntityManager {

	    public void createEntityManager();
	    public void closeEntityManager();
	    public void clearEntityManager();
	    public void shutDown();
	    public void insert(Object entity);
	    public void merge(Object entity);
	    public void remove(Object entity);
	    public <T> T findById(Class<T> entityClazz, Object id);
	    public List<?> findByQuery(String queryString);
	    public List<?> findByQuery(String queryString, String paramater, Object parameterValue);
		EntityManager getEntityManager();
}
