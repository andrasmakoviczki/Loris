package edu.elte.spring.loris.backend.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import edu.elte.spring.loris.backend.entity.User;
import edu.elte.spring.loris.backend.entity.UserHBase;


public class HbaseEntityManagerImpl extends AbstractDaoFactory implements HBaseEntityManager {
	/// Ide jönnek majd a CRUD metódusok megvalósítása
	
	static Logger log = Logger.getLogger(HbaseEntityManagerImpl.class);

	@PersistenceContext (unitName="hbase-pu", type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	@PersistenceUnit (unitName="hbase-pu")
	private EntityManagerFactory emf;

	public HbaseEntityManagerImpl(String persistenceUnitName) {
		if (emf == null) {
			try {
				emf = createEntityManagerFactory(persistenceUnitName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void createEntityManager() {
		if (em == null) {
			em = emf.createEntityManager();
		}
	}
	
	@Override
	public void createUser(){
        User user = new User();
        user.setUserId("0001");
        user.setFirstName("John");
        user.setLastName("Smith");

        em.persist(user);
	}

	@Override
	public void closeEntityManager() {
		if (em != null) {
			em.clear();
			em.close();
			em = null;
		}
	}

	@Override
	public void close() {
		if (emf != null) {
			emf.close();
		}
	}

    @Override
    public List<UserHBase> getAllUsers()
    {

        Query q = em.createQuery("select u from UserHBase u");

        List<UserHBase> users = q.getResultList();

        return users;
    }
}
