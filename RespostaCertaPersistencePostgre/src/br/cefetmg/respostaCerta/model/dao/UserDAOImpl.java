/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

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
import java.sql.Types;
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
public class UserDAOImpl implements UserDAO{

    private static UserDAOImpl userDAO = null;    
    private static long userCount;
    private EntityManagerFactory factory;
    
    /**
     *
     */
    public UserDAOImpl() { 
        userCount = 0;
        factory = Persistence.createEntityManagerFactory("User");
    }

    /**
     *
     * @return
     */
    public static UserDAOImpl getInstance() {
        
        if (userDAO == null)
            userDAO = new UserDAOImpl();
        
        return  userDAO;
    }
    
    /**
     *
     * @param user
     * @throws PersistenceException
     */
    @Override
    synchronized public void insert(User user) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.persist(user);
        man.close();
    }
    
    /**
     *
     * @param user
     * @throws PersistenceException
     */
    @Override
    synchronized public void update(User user) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.merge(user);
        man.close();
    }

    /**
     *
     * @param userId
     * @return
     * @throws PersistenceException
     */
    @Override
    synchronized public User delete(Long userId) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        User u = man.find(User.class, userId);
        man.remove(u);
        man.close();
        return u;
    }

    /**
     *
     * @param userId
     * @return
     * @throws PersistenceException
     */
    @Override
    public User getUserById(Long userId) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        User u = man.find(User.class, userId);
        man.close();
        return u;
    }

    /**
     *
     * @return
     * @throws PersistenceException
     */
    @Override
    public List<User> listAll() throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        List<User> u = man.createQuery("from User").getResultList();
        man.close();
        return u;
    }

    @Override
    public User getUserByLogin(String email, String senha) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        List<User> u = man.createQuery("from user m where m.loginUsuario= " + email + " and m.senhaUsuario= " + senha).getResultList();
        man.close();
        return u.get(0);
    }
    
    @Override
    public List<User> getUserByIdt(char idt) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        List<User> u = man.createQuery("from user m where m.idtUsuario= " + idt).getResultList();
        man.close();
        return u;
    }
    
}
