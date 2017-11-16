/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.Forum;
import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.domain.Topic;
import br.cefetmg.respostaCerta.model.domain.TopicAnswer;
import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.util.db.ConnectionManager;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
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
        factory = Persistence.createEntityManagerFactory("TopicAnswer");
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
        man.persist(topicAnswer);
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
        man.merge(topicAnswer);
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
        TopicAnswer a = man.find(TopicAnswer.class, topicAnswerId);
        man.remove(a);
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
        TopicAnswer a = man.find(TopicAnswer.class, topicAnswerId);
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
        List<TopicAnswer> a = man.createQuery("from TopicAnswer").getResultList();
        man.close();
        return a;
    }
    
    
    @Override
    public List<TopicAnswer> getTopicAnswer(Long mensagemID) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        List<TopicAnswer> a = man.createQuery("from topicanswer m where m.mensagem=" + mensagemID).getResultList();
        man.close();
        return a;
    }
    
}
