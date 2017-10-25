/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.server.business;

import br.cefetmg.respostaCerta.model.dao.ModuleDAOImpl;
import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.server.ModuleManagement;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author adalbs
 */
public class ModuleManagementImpl implements ModuleManagement{
    private final br.cefetmg.respostaCerta.model.service.ModuleManagement moduleManagement;
    
    public ModuleManagementImpl(){
        this.moduleManagement = new br.cefetmg.respostaCerta.model.service.ModuleManagementImpl(new ModuleDAOImpl());
    }
    /**
     *
     * @param module
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void registerModule(Module module) throws BusinessException, PersistenceException, RemoteException{
        if(module==null){
            throw new BusinessException("Modulo não pode ser nulo");
        }
        if(module.getDominio()==null){
            throw new BusinessException("Dominio não pode ser nulo");
        }
        if(module.getNomeModulo()==null){
            throw new BusinessException("Nome do modulo não pode ser nulo");
        }
        moduleManagement.registerModule(module);
    }

    /**
     *
     * @param id // id do mudlo que se deseja atualizar
     * @param module
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void updateModule(Long id, Module module) throws BusinessException, PersistenceException, RemoteException{
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
        moduleManagement.updateModule(id, module);
    }

    /**
     *
     * @param id
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void removeModule(Long id) throws BusinessException, PersistenceException, RemoteException{
        if(id==null){
            throw new BusinessException("ID não pode ser nulo");
        }
        moduleManagement.removeModule(id);
    }

    /**
     *
     * @param id
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public Module getModuleById(Long id) throws BusinessException, PersistenceException, RemoteException{
        if(id==null){
            throw new BusinessException("ID não pode ser nulo");
        } 
        return moduleManagement.getModuleById(id);
    }

    @Override
    public List<Module> getModulesSubject(Long subjectId) throws BusinessException, PersistenceException, RemoteException{
        if(subjectId==null){
            throw new BusinessException("ID não pode ser nulo");
        } 
        return moduleManagement.getModulesSubject(subjectId);
    }

    @Override
    public List<Module> getAllModules() throws BusinessException, PersistenceException, RemoteException{
        return moduleManagement.getAllModules();
    }

    @Override
    public List<Module> searchModules(String busca) throws BusinessException, PersistenceException, RemoteException{
        if(busca==null || busca.equals("")){
            throw new BusinessException("Busca Vazia");
        }
        return moduleManagement.searchModules(busca);
    }
    
}
