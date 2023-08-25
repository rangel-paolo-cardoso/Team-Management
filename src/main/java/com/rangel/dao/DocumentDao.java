package com.rangel.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.rangel.model.Document;

public class DocumentDao extends GenericDao<Document, Integer> {

    EntityManager em = new ApplicationDB().getEntityManager();

    @Override
    public void save(Document obj) {
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
    }

    @Override
    public List<Document> list() {
        List<Document> documents;
        TypedQuery<Document> query = em.createQuery("SELECR d FROM Document d", Document.class);
        documents = query.getResultList();
        return documents;
    }

    @Override
    public void edit(Document obj) {
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Integer id) {
        Document document = em.find(Document.class, id);

        em.getTransaction().begin();
        em.remove(document);
        em.getTransaction().commit();
    }
    
}
