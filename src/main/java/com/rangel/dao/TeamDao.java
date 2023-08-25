package com.rangel.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.rangel.model.Team;

public class TeamDao extends GenericDao<Team, Integer> {

    EntityManager em = new ApplicationDB().getEntityManager();

    @Override
    public void save(Team obj) {
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
    }

    @Override
    public List<Team> list() {
        List<Team> teams;
        TypedQuery<Team> query = em.createQuery("SELECT t FROM Team t", Team.class);
        teams = query.getResultList();
        return teams;
    }

    @Override
    public void edit(Team obj) {
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Integer id) {
        Team team = em.find(Team.class, id);

        em.getTransaction().begin();
        em.remove(team);
        em.getTransaction().commit();
    }
    
}
