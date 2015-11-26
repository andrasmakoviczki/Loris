package edu.elte.spring.loris.backend.dao;

import java.util.List;

import edu.elte.spring.loris.backend.entity.User;

public interface UserDao {
	// User id alapján
	public User getUser(String id);

	// User listázása
	public List<User> listUser();

	// User listázása felhasználónév alapján
	public User findUserbyUsername(String username);
}
