package edu.elte.spring.loris.backend.service;

import java.util.List;

import edu.elte.spring.loris.backend.entity.User;
import edu.elte.spring.loris.backend.util.exception.UserException;

public interface UserService {
	// User létrehozása
	public void createUser(User u) throws UserException;

	// User törlése
	public void removeUser(User u);

	// User frissítése
	public void updateUser(User u);

	// User id alapján
	public User getUser(String id);

	// User felhasználónév alapján
	public User getUserbyUsername(String username);

	// Aktuális user
	public User getCurrentUser() throws UserException;

	// User listázása
	public List<User> listUser();
}
