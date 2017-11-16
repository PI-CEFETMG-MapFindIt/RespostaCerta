/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.Forum;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Vitor
 */
public class ForumDAOImpl implements ForumDAO{
    
    private static ForumDAOImpl forumDAO = null;        
    private static long forumCount;
    private EntityManagerFactory factory;
    
    public ForumDAOImpl() { 
        forumCount = 0;
        factory = Persistence.createEntityManagerFactory("Forum");
    }

    /**
     *
     * @return
     */
    public static ForumDAOImpl getInstance() {
        
        if (forumDAO == null)
            forumDAO = new ForumDAOImpl();
        
        return  forumDAO;
    }
    
    /**
     *
     * @param forum
     * @throws PersistenceException
     */
    @Override
    synchronized public void insert(Forum forum) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.persist(forum);
        man.close();
    }
    
    /**
     *
     * @param forum
     * @throws PersistenceException
     */
    @Override
    synchronized public void update(Forum forum) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.merge(forum);
        man.close();
    }

    /**
     *
     * @param forumId
     * @return
     * @throws PersistenceException
     */
    @Override
    synchronized public Forum delete(Long forumId) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        Forum f = man.find(Forum.class, forumId);
        man.remove(f);
        man.close();
        return f;
    }

    /**
     *
     * @param forumId
     * @return
     * @throws PersistenceException
     */
    @Override
    public Forum getForumById(Long forumId) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        Forum f = man.find(Forum.class, forumId);
        man.close();
        return f;
    }

    /**
     *
     * @return
     * @throws PersistenceException
     */
    @Override
    public List<Forum> listAll() throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        List<Forum> f = man.createQuery("from Forum").getResultList();
        man.close();
        return f;
    }
    
}
