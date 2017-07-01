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
public interface UserDAO {
    public void insert(User usuario) throws PersistenceException;
    public void update(User usuario) throws PersistenceException;
    public User delete(Long usuarioId) throws PersistenceException;
    public User  getUserById(Long usuarioId) throws PersistenceException;
    public List<User> listAll() throws PersistenceException;
    public User getUserByLogin(String email, String senha) throws PersistenceException;
}
