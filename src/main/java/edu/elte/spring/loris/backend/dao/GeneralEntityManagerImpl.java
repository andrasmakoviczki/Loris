package edu.elte.spring.loris.backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.apache.log4j.Logger;

public class GeneralEntityManagerImpl extends AbstractDaoFactory implements GeneralEntityManager {
	
	static Logger log = Logger.getLogger(GeneralEntityManagerImpl.class);
	@PersistenceContext (unitName="hbase-pu", type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	@PersistenceUnit (unitName="hbase-pu")
	private EntityManagerFactory emf;	

	public GeneralEntityManagerImpl(String persistenceUnitName) {
		if (emf == null) {
			try {
				emf = createEntityManagerFactory(persistenceUnitName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		createEntityManager();
	}

	@Override
	public void createEntityManager() {
		if (em == null) {
			em = emf.createEntityManager();
		}
	}

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
	public void insert(Object entity) {
		em.persist(entity);
		em.clear();
	}

	@Override
	public void merge(Object entity) {
		em.merge(entity);
		em.clear();
	}

	@Override
	public void remove(Object entity) {
		em.remove(entity);
		em.clear();
	}

	@Override
	public <T> T findById(Class<T> entityClazz, Object id) {
		T results = em.find(entityClazz, id);
		return results;
	}

	@Override
	public List<?> findByQuery(String queryString) {
		log.info(queryString);
		Query query = em.createQuery(queryString);
		List<?> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<?> findByQuery(String queryString, String paramater, Object parameterValue) {
		Query query = em.createQuery(queryString);
		query.setParameter(paramater, parameterValue);
		log.info(queryString);
		List<?> resultList = query.getResultList();
		return resultList;
	}
}
