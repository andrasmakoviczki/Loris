package edu.elte.spring.loris.backend.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.elte.spring.loris.backend.dao.model.GeneralEntityManagerImpl;
import edu.elte.spring.loris.backend.entity.Category;
import edu.elte.spring.loris.backend.entity.User;
@Repository
public class UserDaoImpl extends GeneralEntityManagerImpl<User> implements UserDao {

	@Override
	public User getUser(String id) {
		User u = findById(User.class, id);
		return u;
	}

	@Override
	public List<User> listUser() {
		List<?> q = findByQuery("SELECT u FROM User u");

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

		String query = new String("SELECT u FROM " + User.class.getSimpleName() + " u WHERE u.username=:uname");
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
