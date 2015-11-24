package edu.elte.spring.loris.backend.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.elte.spring.loris.backend.dao.model.GeneralEntityManagerImpl;
import edu.elte.spring.loris.backend.entity.Category;
import edu.elte.spring.loris.backend.entity.User;
import org.springframework.stereotype.Repository;
@Repository
public class UserDaoImpl extends GeneralEntityManagerImpl<User> implements UserDao {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	/*public UserDaoImpl() {
		em = new GeneralEntityManagerImpl("hbase-pu");
	}*/

	@Override
	public User findUser(String id) {
		User u = findById(User.class, id);
		return u;
	}

	@Override
	public List<User> listUser() {
		List<?> q = findByQuery("select u from User u");

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
		List<?> q = findByQuery(query, "uname", username);

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

}
