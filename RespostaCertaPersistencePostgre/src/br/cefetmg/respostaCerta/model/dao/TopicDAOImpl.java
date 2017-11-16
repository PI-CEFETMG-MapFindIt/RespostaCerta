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
import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.util.db.ConnectionManager;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
public class TopicDAOImpl implements TopicDAO{

    private static TopicDAOImpl topicDAO = null;       
    private static long topicCount;
    private EntityManagerFactory factory;
    
    /**
     *
     */
    public TopicDAOImpl() { 
        topicCount = 0;
        factory = Persistence.createEntityManagerFactory("Topic");
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
        man.persist(topic);
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
        man.merge(topic);
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
        Topic t = man.find(Topic.class, topicId);
        man.remove(t);
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
        Topic t = man.find(Topic.class, topicId);
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
        List<Topic> t = man.createQuery("from Topic").getResultList();
        man.close();
        return t;
    }
    
    
    @Override
    public List<Topic> getForumTopic(Long forumID) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        List<Topic> t = man.createQuery("from topic m where m.forum=" + forumID).getResultList();
        man.close();
        return t;
    }
}
