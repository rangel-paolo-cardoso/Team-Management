package com.rangel.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ApplicationDB {
    
    private final EntityManager em;
    private final EntityManagerFactory emf;

    public ApplicationDB() {
        this.emf = Persistence.createEntityManagerFactory("crudHibernatePU");
        this.em = emf.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return em;
    }
}
