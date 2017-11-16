/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.domain.Subject;
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
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.criterion.MatchMode;

/**
 *
 * @author Vitor
 */
public class OpenQuestionDAOImpl implements OpenQuestionDAO{
    private static OpenQuestionDAOImpl openQuestionDAO = null;     
    private static long openQuestionCount;
    private EntityManagerFactory factory;
    
    /**
     *
     */
    public OpenQuestionDAOImpl() { 
        openQuestionCount = 0;
        factory = Persistence.createEntityManagerFactory("OpenQuestion");
    }

    /**
     *
     * @return
     */
    public static OpenQuestionDAOImpl getInstance() {
        
        if (openQuestionDAO == null)
            openQuestionDAO = new OpenQuestionDAOImpl();
        
        return  openQuestionDAO;
    }
    
    /**
     *
     * @param openQuestion
     * @throws PersistenceException
     */
    @Override
    synchronized public void insert(Question openQuestion) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.persist(openQuestion);
        man.close();
    }
    
    /**
     *
     * @param openQuestion
     * @throws PersistenceException
     */
    @Override
    synchronized public void update(Question openQuestion) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.merge(openQuestion);
        man.close();
    }

    /**
     *
     * @param openQuestionId
     * @return
     * @throws PersistenceException
     */
    @Override
    synchronized public Question delete(Long openQuestionId) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        Question q = man.find(Question.class, openQuestionId);
        man.remove(q);
        man.close();
        return q;
    }

    /**
     *
     * @param openQuestionId
     * @return
     * @throws PersistenceException
     */
    @Override
    public Question getOpenQuestionById(Long openQuestionId) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        Question q = man.find(Question.class, openQuestionId);
        man.close();
        return q;
    }

    /**
     *
     * @return
     * @throws PersistenceException
     */
    @Override
    public List<Question> listAll() throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        List<Question> q = man.createQuery("from Question").getResultList();
        man.close();
        return q;
    }

    @Override
    public List<Question> getOpenQuestionsByUser(Long userId) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        List<Question> resp = man.createQuery("from Question m where m.criador=" + userId).getResultList();
        man.close();
        return resp;
    }

    @Override
    public List<Question> searchQuestion(String parameter) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        List<Question> resp = man.createQuery("from Question m where m.tituloQuestao like :searchkey or m.enunciadoQuestao like :searchkey").setParameter("searchKey", MatchMode.ANYWHERE.toMatchString(parameter)).getResultList();
        man.close();
        return resp;
    }

    @Override
    public List<Question> getOpenQuestionByModule(Long id) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        List<Question> resp = man.createQuery("from Question m where m.modulo=" + id).getResultList();
        man.close();
        return resp;
    }
}
