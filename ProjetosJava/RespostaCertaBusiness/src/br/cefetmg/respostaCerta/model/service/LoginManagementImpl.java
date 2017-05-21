/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.dao.UserDAO;
import java.util.List;
import java.util.LinkedList;
/**
 *
 * @author adalbs
 */
public class LoginManagementImpl implements LoginManagement{
    
    private final UserDAO userDAO;
    
    public LoginManagementImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
   
    /**
     *
     * @param username
     * @param password
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public User loginUser(String username, String password) throws BusinessException, PersistenceException {
        if(username == null || password == null || username.equals("") || password.equals("")){
            throw new BusinessException("Dados de login não podem ser vazios");
        }
        List<User> li ;
        li = userDAO.listAll();
        for(User user: li){
            if (user.getLoginUsuario().equals(username) && user.getSenhaUsuario().equals(password)){
                return user;
            }
        }
        throw new BusinessException("Usuario inexistente");
    }
    
}
