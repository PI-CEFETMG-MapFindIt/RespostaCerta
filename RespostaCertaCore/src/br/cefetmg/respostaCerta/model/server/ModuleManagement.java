/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.server;

import br.cefetmg.respostaCerta.model.service.*;
import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author umcan
 */
public interface ModuleManagement extends Remote{
    public void registerModule(Module module) throws BusinessException, PersistenceException, RemoteException;
    public void updateModule(Long id, Module module) throws BusinessException, PersistenceException, RemoteException;
    public void removeModule(Long id) throws BusinessException, PersistenceException, RemoteException;
    public Module getModuleById(Long id) throws BusinessException, PersistenceException, RemoteException;
    public List<Module> getModulesSubject(Long subjectId) throws BusinessException, PersistenceException, RemoteException;
    public List<Module> getAllModules() throws BusinessException, PersistenceException, RemoteException;
    public List<Module> searchModules(String busca) throws BusinessException, PersistenceException, RemoteException;
}
