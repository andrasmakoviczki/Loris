package edu.elte.spring.loris.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.elte.spring.loris.backend.dao.GeneralEntityManagerImpl;
import edu.elte.spring.loris.backend.entity.Channel;
import edu.elte.spring.loris.backend.entity.User;

public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(ChannelServiceImpl.class);

	private GeneralEntityManagerImpl em;
	
	public UserServiceImpl() {
		em = new GeneralEntityManagerImpl("hbase-pu");
	}

	public GeneralEntityManagerImpl getEm() {
		return em;
	}

	public void setEm(GeneralEntityManagerImpl em) {
		this.em = em;
	}
	
	@Override
	public void insertUser(User u) {
		em.insert(u);
		logger.info("Channel {} information successfully inserted.", u);
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
		
		String query = new String("select u from " + User.class.getSimpleName() + " u where u.username=:uname");
		List<?> q = em.findByQuery(query, "uname", username);

		List<User> uList = new ArrayList<>();
		for (Object object : q) {
			if (object instanceof User) {
				uList.add((User) object);
			}
		}
		
		return uList.get(0);
	}

}
