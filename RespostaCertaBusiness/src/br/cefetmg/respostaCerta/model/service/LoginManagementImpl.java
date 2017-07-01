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
    public User loginUser(String email, String password) throws BusinessException, PersistenceException {
        if(email == null || password == null || email.equals("") || password.equals("")){
            throw new BusinessException("Dados de login não podem ser vazios");
        }
        return userDAO.getUserByLogin(email, password);
    }
    
}
