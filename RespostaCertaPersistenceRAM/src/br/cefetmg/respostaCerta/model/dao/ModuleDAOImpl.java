/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author umcan
 */
public class ModuleDAOImpl implements ModuleDAO{

    private static ModuleDAOImpl moduleDAO = null;        

    private static final HashMap<Long, Module> moduleDB = new HashMap<>();    
    private static long moduleCount;
    
    /**
     *
     */
    public ModuleDAOImpl() { 
        moduleCount = 0;
    }

    /**
     *
     * @return
     */
    public static ModuleDAOImpl getInstance() {
        
        if (moduleDAO == null)
            moduleDAO = new ModuleDAOImpl();
        
        return  moduleDAO;
    }
    
    /**
     *
     * @param module
     * @throws PersistenceException
     */
    @Override
    synchronized public void insert(Module module) throws PersistenceException {

        if (module == null)
            throw new PersistenceException("Entidade não pode ser nula.");                
        
        Long moduleId = module.getIdModulo();
        
        if ((moduleId != null) && moduleDB.containsKey(moduleId))
            throw new PersistenceException("Duplicação de chave.");
        
        moduleId = ++moduleCount;
        module.setIdModulo(moduleId);
        moduleDB.put(moduleId, module);
    }
    
    /**
     *
     * @param module
     * @throws PersistenceException
     */
    @Override
    synchronized public void update(Module module) throws PersistenceException {

        if (module == null)
            throw new PersistenceException("Entidade não pode ser nula.");              
        
        Long moduleId = module.getIdModulo();

        if (moduleId == null)
            throw new PersistenceException("Chave da entidade não pode ser nulo.");        
        
        if (!moduleDB.containsKey(moduleId))
            throw new PersistenceException("Não existe entidade com a chave " + moduleId + ".");
        
        moduleDB.replace(moduleId, module);
    }

    /**
     *
     * @param moduleId
     * @return
     * @throws PersistenceException
     */
    @Override
    synchronized public Module delete(Long moduleId) throws PersistenceException {
        if (moduleId == null)
            throw new PersistenceException("Chave da entidade não pode ser nulo.");
        
        if (!moduleDB.containsKey(moduleId))
            throw new PersistenceException("Não existe entidade com a chave " + moduleId + ".");
        
        return moduleDB.remove(moduleId);
    }

    /**
     *
     * @param moduleId
     * @return
     * @throws PersistenceException
     */
    @Override
    public Module getModuleById(Long moduleId) throws PersistenceException {
        
        if (moduleId == null)
            throw new PersistenceException("Chave da entidade não pode ser nulo.");
        
        if (!moduleDB.containsKey(moduleId))
            throw new PersistenceException("Não existe entidade com a chave " + moduleId + ".");
        
        return moduleDB.get(moduleId);        
        
    }

    /**
     *
     * @return
     * @throws PersistenceException
     */
    @Override
    public List<Module> listAll() throws PersistenceException {
        List<Module> moduleList = new ArrayList<>();
        
        Iterator<Module> iterator = moduleDB.values().iterator();
	while (iterator.hasNext())
            moduleList.add(iterator.next());
        
        return moduleList;
    }

    @Override
    public List<Module> getModulesSubject(long subjectId) throws PersistenceException {
        List<Module> moduleList = new ArrayList<>();
        Iterator<Module> iterator = moduleDB.values().iterator();
	Module item;
        while (iterator.hasNext()){
            item=iterator.next();
            if(Objects.equals(item.getDominio().getIdDominio(), subjectId)){
                moduleList.add(item);
            }
        }    
        return moduleList;
    }
    
}
