package edu.elte.spring.loris.backend.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.elte.spring.loris.backend.dao.model.GeneralEntityManagerImpl;
import edu.elte.spring.loris.backend.entity.Category;
import edu.elte.spring.loris.backend.entity.User;

public class UserDaoImpl implements UserDao {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	private GeneralEntityManagerImpl em;

	public UserDaoImpl() {
		em = new GeneralEntityManagerImpl("hbase-pu");
	}

	public GeneralEntityManagerImpl getEm() {
		return em;
	}

	public void setEm(GeneralEntityManagerImpl em) {
		this.em = em;
	}

	@Override
	public void insertUser(User u) {
		em.insert(u);
		logger.info("User {} information successfully inserted.", u);
	}

	@Override
	public void removeUser(User u) {
		em.remove(u);
		logger.info("User {} information successfully removed.", u);
	}

	@Override
	public User findUser(String id) {
		User u = em.findById(User.class, id);
		return u;
	}

	@Override
	public List<User> listUser() {
		List<?> q = em.findByQuery("select u from User u");

		List<User> uList = new ArrayList<>();
		for (Object object : q) {
			if (object instanceof Category) {
				uList.add((User) object);
			}
		}

		return uList;
	}

	@Override
	public User findUserbyUsername(String username) {

		String query = new String("select u from " + User.class.getSimpleName() + " u where u.username=:uname");
		List<?> q = em.findByQuery(query, "uname", username);

		List<User> uList = new ArrayList<>();
		for (Object object : q) {
			if (object instanceof User) {
				uList.add((User) object);
			}
		}

		if (uList.isEmpty()) {
			return null;
		}

		return uList.get(0);
	}

	@Override
	public void updateUser(User u) {
		em.merge(u);
		logger.info("User {} information successfully updated.", u);
	}

}
