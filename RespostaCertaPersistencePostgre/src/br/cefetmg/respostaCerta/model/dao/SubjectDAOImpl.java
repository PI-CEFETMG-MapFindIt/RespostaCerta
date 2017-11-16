/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.util.db.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        factory = Persistence.createEntityManagerFactory("Module");
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
        man.persist(subject);
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
        man.merge(subject);
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
        Subject s = man.find(Subject.class, subjectId);
        man.remove(s);
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
        Subject s = man.find(Subject.class, subjectId);
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
        List<Subject> m = man.createQuery("from subject").getResultList();
        man.close();
        return m;
    }

    @Override
    public List<Subject> searchSubjects(String busca) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        List<Subject> m = man.createQuery("from Subject m where m.nomeDominio like :searchkey").setParameter("searchKey", MatchMode.ANYWHERE.toMatchString(busca)).getResultList();
        man.close();
        return m;
    }
    
}
