/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.server.business;

import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.dao.UserDAOImpl;
import br.cefetmg.respostaCerta.model.server.LoginManagement;
import java.rmi.RemoteException;
/**
 *
 * @author adalbs
 */
public class LoginManagementImpl implements LoginManagement{
    
    private final br.cefetmg.respostaCerta.model.service.LoginManagement loginManagement;
    
    public LoginManagementImpl() {
        this.loginManagement = new br.cefetmg.respostaCerta.model.service.LoginManagementImpl(new UserDAOImpl());
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
    public User loginUser(String email, String password) throws BusinessException, PersistenceException, RemoteException{
        if(email == null || password == null || email.equals("") || password.equals("")){
            throw new BusinessException("Dados de login não podem ser vazios");
        }
        return loginManagement.loginUser(email, password);
    }
    
}
