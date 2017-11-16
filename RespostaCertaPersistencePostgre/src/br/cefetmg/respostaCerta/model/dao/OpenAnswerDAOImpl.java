/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.domain.OpenAnswer;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.util.db.ConnectionManager;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Vitor
 */
public class OpenAnswerDAOImpl implements OpenAnswerDAO{

    private static OpenAnswerDAOImpl openAnswerDAO = null; 
    private static long openAnswerCount;
    private EntityManagerFactory factory;
    
    public OpenAnswerDAOImpl() { 
        openAnswerCount = 0;
        factory = Persistence.createEntityManagerFactory("OpenAnswer");
    }

    /**
     *
     * @return
     */
    public static OpenAnswerDAOImpl getInstance() {
        
        if (openAnswerDAO == null)
            openAnswerDAO = new OpenAnswerDAOImpl();
        
        return  openAnswerDAO;
    }
    
    /**
     *
     * @param openAnswer
     * @throws PersistenceException
     */
    @Override
    synchronized public void insert(OpenAnswer openAnswer) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.persist(openAnswer);
        man.close();
    }
    
    /**
     *
     * @param openAnswer
     * @throws PersistenceException
     */
    @Override
    synchronized public void update(OpenAnswer openAnswer) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.merge(openAnswer);
        man.close();
    }

    /**
     *
     * @param openAnswerId
     * @return
     * @throws PersistenceException
     */
    @Override
    synchronized public OpenAnswer delete(Long openAnswerId) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        OpenAnswer resp = man.find(OpenAnswer.class, openAnswerId);
        man.remove(resp);
        man.close();
        return resp;
    }

    /**
     *
     * @param openAnswerId
     * @return
     * @throws PersistenceException
     */
    @Override
    public OpenAnswer getOpenAnswerById(Long openAnswerId) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        OpenAnswer resp = man.find(OpenAnswer.class, openAnswerId);
        man.close();
        return resp;
    }

    /**
     *
     * @return
     * @throws PersistenceException
     */
    @Override
    public List<OpenAnswer> listAll() throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        List<OpenAnswer> resp = man.createQuery("from OpenAnswer").getResultList();
        return resp;
    }
    
}
