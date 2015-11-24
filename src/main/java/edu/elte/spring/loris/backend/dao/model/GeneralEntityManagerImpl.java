package edu.elte.spring.loris.backend.dao.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.hamcrest.core.IsInstanceOf;
import org.springframework.stereotype.Component;

public class GeneralEntityManagerImpl<T> extends AbstractDaoFactory implements GeneralEntityManager<T> {
	
	static Integer counter = 0;
	static Logger log = Logger.getLogger(GeneralEntityManagerImpl.class);
	
	@PersistenceContext (unitName="hbase-pu", type=PersistenceContextType.EXTENDED)
	protected EntityManager em;
	private Class<T> type;

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	@Override
	public void closeEntityManager() {
		if (em != null) {
			em.close();
		}
	}

	@Override
	public void clearEntityManager() {
		if (em != null) {
			em.clear();
		}
	}

	@Override
	public void shutDown() {
		if (em != null) {
			em.close();
		}
	}

	@Override
	public void insert(T entity) {
		em.persist(entity);
		em.clear();
	}

	@Override
	public void merge(T entity) {
		em.merge(entity);
		em.clear();
	}

	@Override
	public void remove(T entity) {
		em.remove(entity);
		em.clear();
	}

	@Override
	public T findById(Class<T> entityClazz, Object id) {
		T results = em.find(entityClazz, id);
		return results;
	}

	@Override
	public List<?> findByQuery(String queryString) {
		log.info(queryString);
		Query query = em.createQuery(queryString);
		query.setMaxResults(Integer.MAX_VALUE);
		List<?> r = query.getResultList();
		return r;
	}

	@Override
	public TypedQuery<T> findByTypedQuery(String queryString, Class<T> entityClazz) {
		log.info(queryString);
		TypedQuery<T> results = em.createQuery(queryString,entityClazz);
		return results;
	}

	@Override
	public List<?> findByQuery(String queryString, Map<String, Object> parameters) {
		return findByQuery(queryString,parameters,Integer.MAX_VALUE);
	}
	
	@Override
	public List<?> findByQuery(String queryString, String paramater, Object parameterValue,Integer maxResult) {
		Map<String,Object> parameters = new HashMap<>();
		parameters.put(paramater, parameterValue);
		return findByQuery(queryString,parameters,maxResult);
	}
	
	@Override
	public List<?> findByQuery(String queryString, String paramater, Object parameterValue) {
		Map<String,Object> parameters = new HashMap<>();
		parameters.put(paramater, parameterValue);
		return findByQuery(queryString,parameters, Integer.MAX_VALUE);
	}

	@Override
	public List<?> findByQuery(String queryString, Map<String, Object> parameters, Integer maxResult) {
		Query query = em.createQuery(queryString);
		query.setMaxResults(maxResult);
		for (Entry<String,Object> e : parameters.entrySet()) {
			query.setParameter(e.getKey(), e.getValue());
		}
		
		log.info(queryString);
		List<?> resultList = query.getResultList();
		
		return resultList;
	}

	@Override
	public List<?> findByQuery(String queryString, Integer maxResult) {
		Map<String,Object> parameters = new HashMap<>();
		return findByQuery(queryString, parameters, maxResult);
	}
}
