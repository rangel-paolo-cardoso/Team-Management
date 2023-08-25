package com.rangel.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.rangel.model.Fan;

public class FanDao extends GenericDao<Fan, Integer> {

    EntityManager em = new ApplicationDB().getEntityManager();

    @Override
    public void save(Fan obj) {
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
    }

    @Override
    public List<Fan> list() {
        List<Fan> fans;
        TypedQuery<Fan> query = em.createQuery("SELECT f FROM Fan f", Fan.class);
        fans = query.getResultList();
        return fans;
    }

    @Override
    public void edit(Fan obj) {
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Integer id) {
        Fan fan = em.find(Fan.class, id);

        em.getTransaction().begin();
        em.remove(fan);
        em.getTransaction().commit();
    }
    
}
