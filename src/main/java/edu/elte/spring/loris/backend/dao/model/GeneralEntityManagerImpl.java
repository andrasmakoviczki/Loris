package edu.elte.spring.loris.backend.dao.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class GeneralEntityManagerImpl<T> implements GeneralEntityManager<T> {

	private static final Logger logger = LoggerFactory.getLogger(GeneralEntityManagerImpl.class);
	
	@PersistenceContext (unitName="hbase-pu", type=PersistenceContextType.EXTENDED)
	protected EntityManager em;

	public EntityManager getEntityManager() {
		return em;
	}

	public void closeEntityManager() {
		if (em != null) {
			em.close();
		}
	}

	public void clearEntityManager() {
		if (em != null) {
			em.clear();
		}
	}

	public void shutDown() {
		if (em != null) {
			em.close();
		}
	}

	public void insert(T entity) {
		logger.debug("INSERT: " + entity);
		em.persist(entity);
		em.clear();
	}

	public void merge(T entity) {
		logger.debug("UPDATE: " + entity);
		em.merge(entity);
		em.clear();
	}

	public void remove(T entity) {
		logger.debug("REMOVE: " + entity);
		em.remove(entity);
		em.clear();
	}

	protected T findById(Class<T> entityClazz, Object id) {
		T results = em.find(entityClazz, id);
		return results;
	}

	protected List<?> findByQuery(String queryString) {
		logger.debug(queryString);
		Query query = em.createQuery(queryString);
		query.setMaxResults(Integer.MAX_VALUE);
		List<?> r = query.getResultList();
		return r;
	}

	protected TypedQuery<T> findByTypedQuery(String queryString, Class<T> entityClazz) {
		logger.debug(queryString);
		TypedQuery<T> results = em.createQuery(queryString,entityClazz);
		return results;
	}

	protected List<?> findByQuery(String queryString, Map<String, Object> parameters) {
		return findByQuery(queryString,parameters,Integer.MAX_VALUE);
	}
	
	protected List<?> findByQuery(String queryString, String paramater, Object parameterValue,Integer maxResult) {
		Map<String,Object> parameters = new HashMap<>();
		parameters.put(paramater, parameterValue);
		return findByQuery(queryString,parameters,maxResult);
	}
	
	protected List<?> findByQuery(String queryString, String paramater, Object parameterValue) {
		Map<String,Object> parameters = new HashMap<>();
		parameters.put(paramater, parameterValue);
		return findByQuery(queryString,parameters, Integer.MAX_VALUE);
	}

	protected List<?> findByQuery(String queryString, Map<String, Object> parameters, Integer maxResult) {
		logger.debug(queryString);
		Query query = em.createQuery(queryString);
		query.setMaxResults(maxResult);
		for (Entry<String,Object> e : parameters.entrySet()) {
			query.setParameter(e.getKey(), e.getValue());
		}
		
		logger.debug(queryString);
		List<?> resultList = query.getResultList();
		
		return resultList;
	}

	protected List<?> findByQuery(String queryString, Integer maxResult) {
		Map<String,Object> parameters = new HashMap<>();
		return findByQuery(queryString, parameters, maxResult);
	}
}
