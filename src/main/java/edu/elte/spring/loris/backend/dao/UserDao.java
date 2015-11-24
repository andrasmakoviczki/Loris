package edu.elte.spring.loris.backend.dao;

import java.util.List;

import edu.elte.spring.loris.backend.dao.model.GeneralEntityManager;
import edu.elte.spring.loris.backend.entity.Category;
import edu.elte.spring.loris.backend.entity.User;

public interface UserDao{
	public User findUser(String id);
	public User findUserbyUsername(String username);
	public List<User> listUser();
}
