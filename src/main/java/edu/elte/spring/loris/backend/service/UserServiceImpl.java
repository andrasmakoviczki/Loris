package edu.elte.spring.loris.backend.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import edu.elte.spring.loris.backend.dao.UserDao;
import edu.elte.spring.loris.backend.dao.UserDaoImpl;
import edu.elte.spring.loris.backend.entity.User;
import edu.elte.spring.loris.backend.util.UserException;

public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(ChannelServiceImpl.class);
	
	UserDao uDao;
	
	public UserServiceImpl() {
		this.uDao = new UserDaoImpl();
	}
	
	@Override
	public void createUser(User user) throws UserException {
		
		if (uDao.findUserbyUsername(user.getUsername()) != null) {
			throw new UserException("User already exists:" + user.getUsername());
		}
		
		uDao.insertUser(user);
	}

	@Override
	public void removeUser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findUser(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> listUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserbyUsername(String username) {
		return uDao.findUserbyUsername(username);
	}

	@Override
	public User getCurrentUser() throws UserException {
		Object u = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String username = new String();
		if (u instanceof String) {
			username = (String) u;
		}
		else if(u instanceof org.springframework.security.core.userdetails.User){
			username = ((org.springframework.security.core.userdetails.User) u).getUsername();
		}
		
		User user = findUserbyUsername(username);
		
		if (user == null) {
			throw new UserException("No such user: " + username);
		}
		
		logger.info("CurrentUser: ",user.toString());
		return user;
	}


}
