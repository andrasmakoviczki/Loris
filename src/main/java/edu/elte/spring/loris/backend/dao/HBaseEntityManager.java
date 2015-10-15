package edu.elte.spring.loris.backend.dao;

import java.util.List;

import javax.persistence.Query;

import edu.elte.spring.loris.backend.entity.UserHBase;


public interface HBaseEntityManager {
	//Ide jönnek a CRUD metódusok
	//Create methods
	public void createEntityManager();
	public void createUser();
	
	//Read methods
    public List<UserHBase> getAllUsers();

	//Update methods
	
	//Delete methods
	
	//Close methods
	public void closeEntityManager();
	public void close();

}
