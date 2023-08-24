package com.rangel.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.rangel.model.Player;

public class PlayerDao extends GenericDao<Player, Integer> {

    EntityManager em = new ApplicationDB().getEntityManager();

    @Override
    public void save(Player obj) {
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
    }

    @Override
    public List<Player> list() {
        List<Player> players;
        TypedQuery<Player> query = em.createQuery("SELECT p FROM p", Player.class);
        players = query.getResultList();
        return players;
    }

    @Override
    public void edit(Player obj) {
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Integer id) {
        Player player = em.find(Player.class, id);

        em.getTransaction().begin();
        em.remove(player);
        em.getTransaction().commit();
    }
    
}
