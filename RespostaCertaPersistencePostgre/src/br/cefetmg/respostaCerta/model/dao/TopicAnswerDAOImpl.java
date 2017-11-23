/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.TopicAnswer;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Vitor & Pedro Almeida
 */
public class TopicAnswerDAOImpl implements TopicAnswerDAO{

    private static TopicAnswerDAOImpl topicAnswerDAO = null;    
    private static long topicAnswerCount;
    private EntityManagerFactory factory;
    
    /**
     *
     */
    public TopicAnswerDAOImpl() { 
        topicAnswerCount = 0;
        factory = Persistence.createEntityManagerFactory("RespostaCerta");
    }

    /**
     *
     * @return
     */
    public static TopicAnswerDAOImpl getInstance() {
        
        if (topicAnswerDAO == null)
            topicAnswerDAO = new TopicAnswerDAOImpl();
        
        return  topicAnswerDAO;
    }
    
    /**
     *
     * @param topicAnswer
     * @throws PersistenceException
     */
    @Override
    synchronized public void insert(TopicAnswer topicAnswer) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        man.persist(topicAnswer);
        man.getTransaction().commit();
        man.close();
    }
    
    /**
     *
     * @param topicAnswer
     * @throws PersistenceException
     */
    @Override
    synchronized public void update(TopicAnswer topicAnswer) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        man.merge(topicAnswer);
        man.getTransaction().commit();
        man.close();
    }

    /**
     *
     * @param topicAnswerId
     * @return
     * @throws PersistenceException
     */
    @Override
    synchronized public TopicAnswer delete(Long topicAnswerId) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        TopicAnswer a = man.find(TopicAnswer.class, topicAnswerId);
        man.remove(a);
        man.getTransaction().commit();
        man.close();
        return a;
    }

    /**
     *
     * @param topicAnswerId
     * @return
     * @throws PersistenceException
     */
    @Override
    public TopicAnswer getTopicAnswerById(Long topicAnswerId) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        TopicAnswer a = man.find(TopicAnswer.class, topicAnswerId);
        man.getTransaction().commit();
        man.close();
        return a;         
    }

    /**
     *
     * @return
     * @throws PersistenceException
     */
    @Override
    public List<TopicAnswer> listAll() throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        List<TopicAnswer> a = man.createQuery("Select m from TopicAnswer m", TopicAnswer.class).getResultList();
        man.getTransaction().commit();
        man.close();
        return a;
    }
    
    
    @Override
    public List<TopicAnswer> getTopicAnswer(Long mensagemID) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        List<TopicAnswer> a = man.createQuery("Select m from topicanswer m where m.mensagem=" + mensagemID, TopicAnswer.class).getResultList();
        man.getTransaction().commit();
        man.close();
        return a;
    }
    
}
