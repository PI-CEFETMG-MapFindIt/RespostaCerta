/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.dao.ModuleDAO;
import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;

/**
 *
 * @author adalbs
 */
public class ModuleManagementImpl implements ModuleManagement{
    private final ModuleDAO moduleDAO;
    
    public ModuleManagementImpl (ModuleDAO moduleDAO){
        this.moduleDAO = moduleDAO;
    }
    /**
     *
     * @param module
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public Long registerSubject(Module module) throws BusinessException, PersistenceException {
        moduleDAO.insert(module);
        return moduleDAO.getModuleById(module.getIdModulo()).getIdModulo();
    }

    /**
     *
     * @param id // id do mudlo que se deseja atualizar
     * @param module
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void updateSubject(Long id, Module module) throws BusinessException, PersistenceException {
        module.setIdModulo(id);
        moduleDAO.update(module);
    }

    /**
     *
     * @param id
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void removeSubject(Long id) throws BusinessException, PersistenceException {
        moduleDAO.delete(id);
    }

    /**
     *
     * @param id
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public Module getSubjectById(Long id) throws BusinessException, PersistenceException {
         return moduleDAO.getModuleById(id);
    }
    
}
