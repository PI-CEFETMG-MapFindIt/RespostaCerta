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
import java.util.Objects;

/**
 *
 * @author adalbs
 */
public class UserManagementImpl implements UserManagement {

    private final UserDAO userDAO;

    /**
     *
     * @param userDAO
     */
    public UserManagementImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
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
    public void registerUser(User user) throws BusinessException, PersistenceException {
        if(user==null){
            throw new BusinessException("Usuario não pode ser nulo");
        }
        if (user.getLoginUsuario() == null) {
            throw new BusinessException("login do usuario não pode ser null");
        }
        if (user.getSenhaUsuario() == null) {
            throw new BusinessException("senha não pode ser null");
        }
        if (user.getNomeUsuario() == null) {
            throw new BusinessException("nome usuario não pode ser null");
        }
        userDAO.insert(user);

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
    public void updateUser(Long id, User user) throws BusinessException, PersistenceException {
        user.setIdUsuario(id);
        if(user==null){
            throw new BusinessException("Usuario não pode ser nulo");
        }
        if (user.getLoginUsuario() == null) {
            throw new BusinessException("login do usuario não pode ser null");
        }
        if (user.getSenhaUsuario() == null) {
            throw new BusinessException("senha não pode ser null");
        }
        if (user.getNomeUsuario() == null) {
            throw new BusinessException("nome usuario não pode ser null");
        }
        if(id==null){
            throw new BusinessException("ID não pode ser nulo");
        }
        userDAO.update(user);
        if(userDAO.getUserById(id) != user){
            throw new PersistenceException("Erro de persistencia");
        }
       
    }

    /**
     *
     * @param id
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void removeUser(Long id) throws BusinessException, PersistenceException {
        if(id == null){
            throw new PersistenceException ("ID de busca nulo");
        }
        userDAO.delete(id);
    }
    
    /**
     *
     * @param id
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public User getUserById(Long id) throws BusinessException, PersistenceException {
        if(id == null){
            throw new BusinessException ("ID não pose ser nulo");
        }
        return userDAO.getUserById(id);
    }

}
