package edu.elte.spring.loris.backend.service;

import java.util.List;

import edu.elte.spring.loris.backend.entity.User;
import edu.elte.spring.loris.backend.util.UserException;

public interface UserService {
	public void createUser(User user) throws UserException;
	public void removeUser();
	public User findUser(String id);
	public User getCurrentUser() throws UserException;
	public User findUserbyUsername(String username);
	public List<User> listUser();
}
