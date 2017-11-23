/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.Subject;
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
public class SubjectDAOImpl implements SubjectDAO{

    private static SubjectDAOImpl subjectDAO = null;    
    private static long subjectCount;
    private EntityManagerFactory factory;
    
    /**
     *
     */
    public SubjectDAOImpl() { 
        subjectCount = 0;
        factory = Persistence.createEntityManagerFactory("RespostaCerta");
    }

    /**
     *
     * @return
     */
    public static SubjectDAOImpl getInstance() {
        
        if (subjectDAO == null)
            subjectDAO = new SubjectDAOImpl();
        
        return  subjectDAO;
    }
    
    /**
     *
     * @param subject
     * @throws PersistenceException
     */
    @Override
    synchronized public void insert(Subject subject) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        man.persist(subject);
        man.getTransaction().commit();
        man.close();
    }
    
    /**
     *
     * @param subject
     * @throws PersistenceException
     */
    @Override
    synchronized public void update(Subject subject) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        man.merge(subject);
        man.getTransaction().commit();
        man.close();
    }

    /**
     *
     * @param subjectId
     * @return
     * @throws PersistenceException
     */
    @Override
    synchronized public Subject delete(Long subjectId) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        Subject s = man.find(Subject.class, subjectId);
        man.remove(s);
        man.getTransaction().commit();
        man.close();
        return s;
    }

    /**
     *
     * @param subjectId
     * @return
     * @throws PersistenceException
     */
    @Override
    public Subject getSubjectById(Long subjectId) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        Subject s = man.find(Subject.class, subjectId);
        man.getTransaction().commit();
        man.close();
        return s;
    }

    /**
     *
     * @return
     * @throws PersistenceException
     */
    @Override
    public List<Subject> listAll() throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        List<Subject> m = man.createQuery("Select m from subject m", Subject.class).getResultList();
        man.getTransaction().commit();
        man.close();
        return m;
    }

    @Override
    public List<Subject> searchSubjects(String busca) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        List<Subject> m = man.createQuery("Select m from Subject m where m.nomeDominio like :searchkey", Subject.class).setParameter("searchKey", MatchMode.ANYWHERE.toMatchString(busca)).getResultList();
        man.getTransaction().commit();
        man.close();
        return m;
    }
    
}
