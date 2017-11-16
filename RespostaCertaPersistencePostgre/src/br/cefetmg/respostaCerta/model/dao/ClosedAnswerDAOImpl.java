/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.util.db.ConnectionManager;
import br.cefetmg.respostaCerta.model.domain.ClosedAnswer;
import br.cefetmg.respostaCerta.model.domain.ClosedQuestion;
import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
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
public class ClosedAnswerDAOImpl implements ClosedAnswerDAO{
    private static ClosedAnswerDAOImpl closedDAO = null;         
    private static long closedCount;
    private EntityManagerFactory factory;
    
    public ClosedAnswerDAOImpl() { 
        closedCount = 0;
        factory = Persistence.createEntityManagerFactory("ClosedAnswer");
    }

    /**
     *
     * @return
     */
    public static ClosedAnswerDAOImpl getInstance() {
        
        if (closedDAO == null)
            closedDAO = new ClosedAnswerDAOImpl();
        
        return  closedDAO;
    }  
    /**
     *
     * @param respostaFechada
     * @throws PersistenceException
     */
    @Override
    synchronized public void insert(ClosedAnswer respostaFechada) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.persist(respostaFechada);
        man.close();
    }
    
    /**
     *
     * @param closedAnswer
     * @throws PersistenceException
     */
    @Override
    synchronized public void update(ClosedAnswer closedAnswer) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.merge(closedAnswer);
        man.close();
    }

    /**
     *
     * @param closedId
     * @return
     * @throws PersistenceException
     */
    @Override
    synchronized public ClosedAnswer delete(Long closedId) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        ClosedAnswer resp = man.find(ClosedAnswer.class, closedId);
        man.remove(resp);
        man.close();
        return resp;
    }

    /**
     *
     * @param closedId
     * @return
     * @throws PersistenceException
     */
    @Override
    public ClosedAnswer getClosedAnswerById(Long closedId) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        ClosedAnswer resp = man.find(ClosedAnswer.class, closedId);
        man.close();
        return resp;
    }

    /**
     *
     * @return
     * @throws PersistenceException
     */
    @Override
    public List<ClosedAnswer> listAll() throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        List<ClosedAnswer> resp = man.createQuery("from ClosedAnswer").getResultList();
        return resp;
    }    

    @Override
    public List<ClosedAnswer> getClosedAnswerByUser(Long userId) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        List<ClosedAnswer> resp = man.createQuery("from ClosedAnswer m where m.autor=" + userId).getResultList();
        return resp;
    }
}