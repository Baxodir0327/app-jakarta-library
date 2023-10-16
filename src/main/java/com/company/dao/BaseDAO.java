package com.company.dao;

import com.company.entity.Auditable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.Serializable;

public abstract class BaseDAO<T extends Auditable,ID extends Serializable> {

    protected static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");
    protected static final EntityManager em=emf.createEntityManager();

    protected void begin(){
        em.getTransaction().begin();
    }

    protected void commit(){
        em.getTransaction().commit();
    }
}
