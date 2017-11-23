/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.Topic;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Vitor & Pedro Almeida
 */
public class TopicDAOImpl implements TopicDAO{

    private static TopicDAOImpl topicDAO = null;       
    private static long topicCount;
    private EntityManagerFactory factory;
    
    /**
     *
     */
    public TopicDAOImpl() { 
        topicCount = 0;
        factory = Persistence.createEntityManagerFactory("RespostaCerta");
    }

    /**
     *
     * @return
     */
    public static TopicDAOImpl getInstance() {
        
        if (topicDAO == null)
            topicDAO = new TopicDAOImpl();
        
        return  topicDAO;
    }
    
    /**
     *
     * @param topic
     * @throws PersistenceException
     */
    @Override
    synchronized public void insert(Topic topic) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        man.persist(topic);
        man.getTransaction().commit();
        man.close();
    }
    
    /**
     *
     * @param topic
     * @throws PersistenceException
     */
    @Override
    synchronized public void update(Topic topic) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        man.merge(topic);
        man.getTransaction().commit();
        man.close();
    }

    /**
     *
     * @param topicId
     * @return
     * @throws PersistenceException
     */
    @Override
    synchronized public Topic delete(Long topicId) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        Topic t = man.find(Topic.class, topicId);
        man.remove(t);
        man.getTransaction().commit();
        man.close();
        return t;
    }

    /**
     *
     * @param topicId
     * @return
     * @throws PersistenceException
     */
    @Override
    public Topic getTopicById(Long topicId) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        Topic t = man.find(Topic.class, topicId);
        man.getTransaction().commit();
        man.close();
        return t;
    }

    /**
     *
     * @return
     * @throws PersistenceException
     */
    @Override
    public List<Topic> listAll() throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        List<Topic> t = man.createQuery("Select m from Topic m", Topic.class).getResultList();
        man.getTransaction().commit();
        man.close();
        return t;
    }
    
    
    @Override
    public List<Topic> getForumTopic(Long forumID) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        List<Topic> t = man.createQuery("Select m from topic m where m.forum=" + forumID, Topic.class).getResultList();
        man.getTransaction().commit();
        man.close();
        return t;
    }
}
