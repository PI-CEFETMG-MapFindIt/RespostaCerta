/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.OpenAnswer;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.List;
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
        factory = Persistence.createEntityManagerFactory("RespostaCerta");
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
        man.getTransaction().begin();
        man.persist(openAnswer);
        man.getTransaction().commit();
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
        man.getTransaction().begin();
        man.merge(openAnswer);
        man.getTransaction().commit();
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
        man.getTransaction().begin();
        OpenAnswer resp = man.find(OpenAnswer.class, openAnswerId);
        man.remove(resp);
        man.getTransaction().commit();
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
        man.getTransaction().begin();
        OpenAnswer resp = man.find(OpenAnswer.class, openAnswerId);
        man.getTransaction().commit();
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
        man.getTransaction().begin();
        List<OpenAnswer> resp = man.createQuery("Select m from OpenAnswer m", OpenAnswer.class).getResultList();
        man.getTransaction().commit();
        return resp;
    }
    
}
