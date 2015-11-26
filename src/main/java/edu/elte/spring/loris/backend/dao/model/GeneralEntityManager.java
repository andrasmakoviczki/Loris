package edu.elte.spring.loris.backend.dao.model;

public interface GeneralEntityManager<T> {
	// Entitás bejegyzése az adatbázisba
	public void insert(T entity);

	// Entitás frissítése az adatbázisban
	public void merge(T entity);

	// Entitás törlése az adatbázisból
	public void remove(T entity);
}
