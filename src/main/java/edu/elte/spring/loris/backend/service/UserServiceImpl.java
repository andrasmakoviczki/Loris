package edu.elte.spring.loris.backend.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.elte.spring.loris.backend.dao.UserDaoImpl;
import edu.elte.spring.loris.backend.entity.User;
import edu.elte.spring.loris.backend.util.exception.UserException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDaoImpl uDao;

	public UserServiceImpl() {
	}

	@Override
	public void createUser(User u) throws UserException {

		if (uDao.findUserbyUsername(u.getUsername()) != null) {
			throw new UserException("User already exists: " + u.getUsername());
		}

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		u.setPassword(passwordEncoder.encode(u.getPassword()));
		u.setCreateDate(new Date());
		u.setLastLogin(new Date());
		u.setEnable(true);

		uDao.insert(u);
	}

	@Override
	public void removeUser(User u) {
		uDao.remove(u);
	}

	@Override
	public void updateUser(User u) {
		uDao.merge(u);
	}

	@Override
	public User getUser(String id) {
		return uDao.getUser(id);
	}

	@Override
	public List<User> listUser() {
		return uDao.listUser();
	}

	@Override
	public User getUserbyUsername(String username) {
		return uDao.findUserbyUsername(username);
	}

	@Override
	public User getCurrentUser() throws UserException {

		String username = new String();
		Object u = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (u instanceof org.springframework.security.core.userdetails.User) {
			username = ((org.springframework.security.core.userdetails.User) u).getUsername();
		}

		User user = getUserbyUsername(username);
		if (user == null) {
			throw new UserException("No such user: " + username);
		}

		return user;
	}
}
