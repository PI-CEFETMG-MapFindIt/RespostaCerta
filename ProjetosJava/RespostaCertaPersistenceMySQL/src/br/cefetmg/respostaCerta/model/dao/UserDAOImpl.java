/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author umcan
 */
public class UserDAOImpl implements UserDAO{

    /**
     *
     * @param usuario
     * @throws PersistenceException
     */
    @Override
    public void insert(User usuario) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param usuario
     * @throws PersistenceException
     */
    @Override
    public void update(User usuario) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param usuarioId
     * @return
     * @throws PersistenceException
     */
    @Override
    public User delete(Long usuarioId) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param usuarioId
     * @return
     * @throws PersistenceException
     */
    @Override
    public User getUserById(Long usuarioId) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     * @throws PersistenceException
     */
    @Override
    public List<User> listAll() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
