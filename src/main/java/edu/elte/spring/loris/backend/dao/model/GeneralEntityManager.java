package edu.elte.spring.loris.backend.dao.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public interface GeneralEntityManager<T> {

	    public void closeEntityManager();
	    public void clearEntityManager();
	    public void shutDown();
	    public void insert(T entity);
	    public void merge(T entity);
	    public void remove(T entity);
	    public T findById(Class<T> entityClazz, Object id);
	    public TypedQuery<T> findByTypedQuery(String queryString,Class<T> entityClazz);
	    public List<?> findByQuery(String queryString);
	    public List<?> findByQuery(String queryString,Integer maxResult);
	    public List<?> findByQuery(String queryString, String paramater, Object parameterValue);
	    public List<?> findByQuery(String queryString, String paramater, Object parameterValue, Integer maxResult);
	    public List<?> findByQuery(String queryString, Map<String,Object> parameters);
	    public List<?> findByQuery(String queryString, Map<String, Object> parameters, Integer maxResult);
	    EntityManager getEntityManager();
}
