/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.ClosedQuestion;
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
public class ClosedQuestionDAOImpl implements ClosedQuestionDAO{
    private static ClosedQuestionDAOImpl closedDAO = null;   
    private static long closedCount;
    private EntityManagerFactory factory;
    
    /**
     *
     */
    public ClosedQuestionDAOImpl() {
        factory = Persistence.createEntityManagerFactory("RespostaCerta");
        closedCount = 0;
    }

    /**
     *
     * @return
     */
    public static ClosedQuestionDAOImpl getInstance() {
        
        if (closedDAO == null)
            closedDAO = new ClosedQuestionDAOImpl();
        
        return  closedDAO;
    }
    
    /**
     *
     * @param questaoFechada
     * @throws PersistenceException
     */
    @Override
    synchronized public void insert(ClosedQuestion questaoFechada) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        man.persist(questaoFechada);
        man.getTransaction().commit();
        man.close();
    }
    
    /**
     *
     * @param closedQuestion
     * @throws PersistenceException
     */
    @Override
    synchronized public void update(ClosedQuestion closedQuestion) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        man.merge(closedQuestion);
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
    synchronized public ClosedQuestion delete(Long closedId) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        ClosedQuestion q = man.find(ClosedQuestion.class, closedId);
        man.remove(q);
        man.getTransaction().commit();
        man.close();
        return q;
    }

    /**
     *
     * @param closedId
     * @return
     * @throws PersistenceException
     */
    @Override
    public ClosedQuestion getClosedQuestionById(Long closedId) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        ClosedQuestion q = man.find(ClosedQuestion.class, closedId);
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
    public List<ClosedQuestion> listAll() throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        List<ClosedQuestion> q = man.createQuery("Select m from ClosedQuestion m", ClosedQuestion.class).getResultList();
        man.getTransaction().commit();
        man.close();
        return q;
    }
    
    @Override
    public List<ClosedQuestion> getClosedQuestionsByUser(Long userId) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        List<ClosedQuestion> resp = man.createQuery("Select m from ClosedQuestion m where m.criador=" + userId, ClosedQuestion.class).getResultList();
        man.getTransaction().commit();
        man.close();
        return resp;
    }

    @Override
    public List<Question> searchClosedQuestion(String parameter) throws PersistenceException{
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        List<Question> resp = man.createQuery("Select m from ClosedQuestion m where m.tituloQuestao like :searchkey or m.enunciadoQuestao like :searchkey", Question.class).setParameter("searchKey", MatchMode.ANYWHERE.toMatchString(parameter)).getResultList();
        man.getTransaction().commit();
        man.close();
        return resp;
    }

    @Override
    public List<Question> getClosedQuestionByModule(Long id) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        List<Question> resp = man.createQuery("Select m from ClosedQuestion m where m.modulo=" + id, Question.class).getResultList();
        man.getTransaction().commit();
        man.close();
        return resp;
    }
}
