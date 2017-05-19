/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.dao.UserDAO;
import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.dao.UserDAO;
import java.util.Objects;

/**
 *
 * @author umcan
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
    public Long registerUser(User user) throws BusinessException, PersistenceException {
        if (user.getLoginUsuario() == null || user.getSenhaUsuario() == null || user.getNomeUsuario() == null) {
            throw new BusinessException("Campos do usuario não podem estar vazios");
        }
        userDAO.insert(user);
        if (Objects.equals(userDAO.getUserById(user.getIdUsuario()), user)) {
            return userDAO.getUserById(user.getIdUsuario()).getIdUsuario();
        } else {
            throw new PersistenceException("Falha ao registrar o usuario");
        }

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
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param id
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void removeUser(Long id) throws BusinessException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
