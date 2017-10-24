/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author umcan
 */
public interface UserManagement extends Remote{
    public void registerUser(User user) throws BusinessException, PersistenceException, RemoteException;
    public void updateUser(Long id, User user) throws BusinessException, PersistenceException, RemoteException;
    public void removeUser(Long id) throws BusinessException, PersistenceException, RemoteException;
    public User getUserById(Long id) throws BusinessException, PersistenceException, RemoteException;
    public List<User> getUserByIdt(char idt) throws BusinessException, PersistenceException, RemoteException;
}
