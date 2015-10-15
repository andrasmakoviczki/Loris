package edu.elte.spring.loris.backend.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

abstract public class AbstractDaoFactory {
    protected EntityManagerFactory createEntityManagerFactory(String persistenceUnitName)
    {
        return Persistence.createEntityManagerFactory(persistenceUnitName);
    }
}
