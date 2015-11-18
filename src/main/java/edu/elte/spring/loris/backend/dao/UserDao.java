package edu.elte.spring.loris.backend.dao;

import java.util.List;

import edu.elte.spring.loris.backend.entity.User;

public interface UserDao {
	public void insertUser(User u);
	public void updateUser(User u);
	public User findUser(String id);
	public User findUserbyUsername(String username);
	public List<User> listUser();
	void removeUser(User u);
}
