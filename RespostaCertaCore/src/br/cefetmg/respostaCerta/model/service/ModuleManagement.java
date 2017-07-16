/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author umcan
 */
public interface ModuleManagement {
    public void registerModule(Module module) throws BusinessException, PersistenceException;
    public void updateModule(Long id, Module module) throws BusinessException, PersistenceException;
    public void removeModule(Long id) throws BusinessException, PersistenceException;
    public Module getModuleById(Long id) throws BusinessException, PersistenceException;
    public List<Module> getModulesSubject(Long subjectId) throws BusinessException, PersistenceException;
    public List<Module> getAllModules() throws BusinessException, PersistenceException;
    public List<Module> searchModules(String busca) throws BusinessException, PersistenceException;
}
