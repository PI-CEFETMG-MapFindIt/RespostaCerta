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
import java.util.List;

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
    public void registerModule(Module module) throws BusinessException, PersistenceException {
        if(module==null){
            throw new BusinessException("Modulo não pode ser nulo");
        }
        if(module.getDominio()==null){
            throw new BusinessException("Dominio não pode ser nulo");
        }
        if(module.getNomeModulo()==null){
            throw new BusinessException("Nome do modulo não pode ser nulo");
        }
        moduleDAO.insert(module);
    }

    /**
     *
     * @param id // id do mudlo que se deseja atualizar
     * @param module
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void updateModule(Long id, Module module) throws BusinessException, PersistenceException {
        if(module==null){
            throw new BusinessException("Modulo não pode ser nulo");
        }
        if(module.getDominio()==null){
            throw new BusinessException("Dominio não pode ser nulo");
        }
        if(module.getNomeModulo()==null){
            throw new BusinessException("Nome do modulo não pode ser nulo");
        }
        if(id==null){
            throw new BusinessException("ID não pode ser nulo");
        }
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
    public void removeModule(Long id) throws BusinessException, PersistenceException {
        if(id==null){
            throw new BusinessException("ID não pode ser nulo");
        }
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
    public Module getModuleById(Long id) throws BusinessException, PersistenceException {
        if(id==null){
            throw new BusinessException("ID não pode ser nulo");
        } 
        return moduleDAO.getModuleById(id);
    }

    @Override
    public List<Module> getModulesSubject(Long subjectId) throws BusinessException, PersistenceException {
        if(subjectId==null){
            throw new BusinessException("ID não pode ser nulo");
        } 
        return moduleDAO.getModulesSubject(subjectId);
    }

    @Override
    public List<Module> getAllModules() throws BusinessException, PersistenceException {
        return moduleDAO.listAll();
    }
    
}
