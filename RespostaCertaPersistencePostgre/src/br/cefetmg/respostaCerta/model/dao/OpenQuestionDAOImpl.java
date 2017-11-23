/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.List;
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
        factory = Persistence.createEntityManagerFactory("RespostaCerta");
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
        man.getTransaction().begin();
        man.persist(openQuestion);
        man.getTransaction().commit();
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
        man.getTransaction().begin();
        man.merge(openQuestion);
        man.getTransaction().commit();
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
        man.getTransaction().begin();
        Question q = man.find(Question.class, openQuestionId);
        man.remove(q);
        man.getTransaction().commit();
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
        man.getTransaction().begin();
        Question q = man.find(Question.class, openQuestionId);
        man.getTransaction().commit();
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
        man.getTransaction().begin();
        List<Question> q = man.createQuery("Select m from Question m", Question.class).getResultList();
        man.getTransaction().commit();
        man.close();
        return q;
    }

    @Override
    public List<Question> getOpenQuestionsByUser(Long userId) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        List<Question> resp = man.createQuery("Select m from Question m where m.criador=" + userId, Question.class).getResultList();
        man.getTransaction().commit();
        man.close();
        return resp;
    }

    @Override
    public List<Question> searchQuestion(String parameter) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        List<Question> resp = man.createQuery("Select m from Question m where m.tituloQuestao like :searchkey or m.enunciadoQuestao like :searchkey", Question.class).setParameter("searchKey", MatchMode.ANYWHERE.toMatchString(parameter)).getResultList();
        man.getTransaction().commit();
        man.close();
        return resp;
    }

    @Override
    public List<Question> getOpenQuestionByModule(Long id) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        List<Question> resp = man.createQuery("Select m from Question m where m.modulo=" + id, Question.class).getResultList();
        man.getTransaction().commit();
        man.close();
        return resp;
    }
}
