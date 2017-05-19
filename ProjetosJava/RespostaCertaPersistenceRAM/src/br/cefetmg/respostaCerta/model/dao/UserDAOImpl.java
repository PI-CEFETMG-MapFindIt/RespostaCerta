/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author umcan
 */
public class UserDAOImpl implements UserDAO{

    private static UserDAOImpl userDAO = null;        

    private static final HashMap<Long, User> userDB = new HashMap<>();    
    private static long userCount;
    
    public UserDAOImpl() { 
        userCount = 0;
    }

    public static UserDAOImpl getInstance() {
        
        if (userDAO == null)
            userDAO = new UserDAOImpl();
        
        return  userDAO;
    }
    
    @Override
    synchronized public void insert(User user) throws PersistenceException {

        if (user == null)
            throw new PersistenceException("Entidade não pode ser nula.");                
        
        Long userId = user.getIdUsuario();
        
        if ((userId != null) && userDB.containsKey(userId))
            throw new PersistenceException("Duplicação de chave.");
        
        userId = ++userCount;
        user.setIdUsuario(userId);
        userDB.put(userId, user);
    }
    
    @Override
    synchronized public void update(User user) throws PersistenceException {

        if (user == null)
            throw new PersistenceException("Entidade não pode ser nula.");              
        
        Long userId = user.getIdUsuario();

        if (userId == null)
            throw new PersistenceException("Chave da entidade não pode ser nulo.");        
        
        if (!userDB.containsKey(userId))
            throw new PersistenceException("Não existe entidade com a chave " + userId + ".");
        
        userDB.replace(userId, user);
    }

    @Override
    synchronized public User delete(Long userId) throws PersistenceException {
        if (userId == null)
            throw new PersistenceException("Chave da entidade não pode ser nulo.");
        
        if (!userDB.containsKey(userId))
            throw new PersistenceException("Não existe entidade com a chave " + userId + ".");
        
        return userDB.remove(userId);
    }

    @Override
    public User getUserById(Long userId) throws PersistenceException {
        
        if (userId == null)
            throw new PersistenceException("Chave da entidade não pode ser nulo.");
        
        if (!userDB.containsKey(userId))
            throw new PersistenceException("Não existe entidade com a chave " + userId + ".");
        
        return userDB.get(userId);        
        
    }

    @Override
    public List<User> listAll() throws PersistenceException {
        List<User> userList = new ArrayList<>();
        
        Iterator<User> iterator = userDB.values().iterator();
	while (iterator.hasNext())
            userList.add(iterator.next());
        
        return userList;
    }
    
}
