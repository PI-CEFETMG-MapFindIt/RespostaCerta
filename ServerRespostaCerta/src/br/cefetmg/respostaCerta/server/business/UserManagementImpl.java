/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.server.business;

import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.dao.UserDAO;
import br.cefetmg.respostaCerta.model.dao.UserDAOImpl;
import br.cefetmg.respostaCerta.model.server.UserManagement;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author adalbs
 */
public class UserManagementImpl implements UserManagement{

    private final br.cefetmg.respostaCerta.model.service.UserManagement userManagement;

    /**
     *
     * @param userDAO
     */
    public UserManagementImpl() {
        this.userManagement = new br.cefetmg.respostaCerta.model.service.UserManagementImpl(new UserDAOImpl());
    }

    /**
     *
     * @param user
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     *  método registra um usario no banco de dados
     */
    @Override
    public void registerUser(User user) throws BusinessException, PersistenceException, RemoteException{
        if(user==null){
            throw new BusinessException("Usuario não pode ser nulo");
        }
        if (user.getLoginUsuario() == null) {
            throw new BusinessException("Login do usuario não pode ser null");
        }
        if (user.getSenhaUsuario() == null) {
            throw new BusinessException("Senha não pode ser null");
        }
        if (user.getNomeUsuario() == null) {
            throw new BusinessException("Nome usuario não pode ser null");
        }
        userManagement.registerUser(user);
    }

    /**
     *
     * @param id // id do usuario que se deseja atualizar
     * @param user// objeto que carrega as atualizações
     * @throws BusinessException
     * @throws PersistenceException
     * métado que atualiza um usuario já registrado
     */
    @Override
    public void updateUser(Long id, User user) throws BusinessException, PersistenceException, RemoteException{
        if(user==null){
            throw new BusinessException("Usuario não pode ser nulo");
        }
        if (user.getLoginUsuario() == null) {
            throw new BusinessException("Login do usuario não pode ser null");
        }
        if (user.getSenhaUsuario() == null) {
            throw new BusinessException("Senha não pode ser null");
        }
        if (user.getNomeUsuario() == null) {
            throw new BusinessException("Nome usuario não pode ser null");
        }
        if(id==null){
            throw new BusinessException("ID não pode ser nulo");
        }
        userManagement.updateUser(id, user);     
    }

    /**
     *
     * @param id
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void removeUser(Long id) throws BusinessException, PersistenceException, RemoteException{
        if(id == null){
            throw new BusinessException("ID de busca nulo");
        }
        userManagement.removeUser(id);
    }
    
    /**
     *
     * @param id
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public User getUserById(Long id) throws BusinessException, PersistenceException, RemoteException{
        if(id == null){
            throw new BusinessException ("ID não pode ser nulo");
        }
        return userManagement.getUserById(id);
    }

    @Override
    public List<User> getUserByIdt(char idt) throws BusinessException, PersistenceException, RemoteException{
        return userManagement.getUserByIdt(idt);
    }

}
