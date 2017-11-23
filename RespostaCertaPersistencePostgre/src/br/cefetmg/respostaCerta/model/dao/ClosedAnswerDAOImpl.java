/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.ClosedAnswer;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.List;
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
        factory = Persistence.createEntityManagerFactory("RespostaCerta");
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
        man.getTransaction().begin();
        man.persist(respostaFechada);
        man.getTransaction().commit();
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
        man.getTransaction().begin();
        man.merge(closedAnswer);
        man.getTransaction().commit();
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
        man.getTransaction().begin();
        ClosedAnswer resp = man.find(ClosedAnswer.class, closedId);
        man.remove(resp);
        man.getTransaction().commit();
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
        man.getTransaction().begin();
        ClosedAnswer resp = man.find(ClosedAnswer.class, closedId);
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
    public List<ClosedAnswer> listAll() throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        List<ClosedAnswer> resp = man.createQuery("Select m from ClosedAnswer m", ClosedAnswer.class).getResultList();
        man.getTransaction().commit();
        man.close();
        return resp;
    }    

    @Override
    public List<ClosedAnswer> getClosedAnswerByUser(Long userId) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        List<ClosedAnswer> resp = man.createQuery("Select m from ClosedAnswer m where m.autor=" + userId, ClosedAnswer.class).getResultList();
        man.getTransaction().commit();
        man.close();
        return resp;
    }
}