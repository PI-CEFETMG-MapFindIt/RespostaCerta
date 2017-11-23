
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.List;
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
        factory = Persistence.createEntityManagerFactory("RespostaCerta");
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
        man.getTransaction().begin();
        man.persist(user);
        man.getTransaction().commit();
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
        man.getTransaction().begin();
        man.merge(user);
        man.getTransaction().commit();
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
        man.getTransaction().begin();
        User u = man.find(User.class, userId);
        man.remove(u);
        man.getTransaction().commit();
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
        man.getTransaction().begin();
        User u = man.find(User.class, userId);
        man.getTransaction().commit();
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
        man.getTransaction().begin();
        List<User> u = man.createQuery("Select m from User m", User.class).getResultList();
        man.getTransaction().commit();
        man.close();
        return u;
    }

    @Override
    public User getUserByLogin(String email, String senha) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        List<User> u = man.createQuery("Select m from User m where m.loginUsuario= '" + email + "' and m.senhaUsuario= '" + senha + "'", User.class).getResultList();
        man.getTransaction().commit();
        man.close();
        try{
            return u.get(0);
        }catch(ArrayIndexOutOfBoundsException ex){
            throw new PersistenceException(ex.getMessage());
        }
    }
    
    @Override
    public List<User> getUserByIdt(char idt) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        List<User> u = man.createQuery("Select m from User m where m.idtUsuario= " + idt, User.class).getResultList();
        man.getTransaction().commit();
        man.close();
        return u;
    }
    
}
