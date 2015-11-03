package edu.elte.spring.loris.backend.service;

import java.util.List;

import edu.elte.spring.loris.backend.entity.User;

public interface UserService {
	public void insertUser(User fe);
	public void removeUser();
	public User findUser(String id);
	public User findUserbyUsername(String username);
	public List<User> listUser();
}
