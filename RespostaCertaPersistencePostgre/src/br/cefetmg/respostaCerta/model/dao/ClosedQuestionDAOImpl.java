/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.ClosedQuestion;
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
public class ClosedQuestionDAOImpl implements ClosedQuestionDAO{
    private static ClosedQuestionDAOImpl closedDAO = null;   
    private static long closedCount;
    private EntityManagerFactory factory;
    
    /**
     *
     */
    public ClosedQuestionDAOImpl() {
        factory = Persistence.createEntityManagerFactory("ClosedQuestion");
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
        man.persist(questaoFechada);
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
        man.merge(closedQuestion);
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
        ClosedQuestion q = man.find(ClosedQuestion.class, closedId);
        man.remove(q);
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
        ClosedQuestion q = man.find(ClosedQuestion.class, closedId);
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
        List<ClosedQuestion> q = man.createQuery("from ClosedAnswer").getResultList();
        man.close();
        return q;
    }
    
    @Override
    public List<ClosedQuestion> getClosedQuestionsByUser(Long userId) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        List<ClosedQuestion> resp = man.createQuery("from ClosedQuestion m where m.criador=" + userId).getResultList();
        man.close();
        return resp;
    }

    @Override
    public List<Question> searchClosedQuestion(String parameter) throws PersistenceException{
        EntityManager man = factory.createEntityManager();
        List<Question> resp = man.createQuery("from ClosedQuestion m where m.tituloQuestao like :searchkey or m.enunciadoQuestao like :searchkey").setParameter("searchKey", MatchMode.ANYWHERE.toMatchString(parameter)).getResultList();
        man.close();
        return resp;
    }

    @Override
    public List<Question> getClosedQuestionByModule(Long id) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        List<Question> resp = man.createQuery("from ClosedQuestion m where m.modulo=" + id).getResultList();
        man.close();
        return resp;
    }
}
