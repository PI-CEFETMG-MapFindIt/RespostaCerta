/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.criterion.MatchMode;

/**
 *
 * @author Vitor
 */
public class ModuleDAOImpl implements ModuleDAO{

    private static ModuleDAOImpl moduleDAO = null;   
    private static long moduleCount;
    private EntityManagerFactory factory;
    
    /**
     *
     */
    public ModuleDAOImpl() { 
        moduleCount = 0;
        factory = Persistence.createEntityManagerFactory("RespostaCerta");
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
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        man.persist(module);
        man.getTransaction().commit();
        man.close();
    }
    
    /**
     *
     * @param module
     * @throws PersistenceException
     */
    @Override
    synchronized public void update(Module module) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        man.merge(module);
        man.getTransaction().commit();
        man.close();
    }

    /**
     *
     * @param moduleId
     * @return
     * @throws PersistenceException
     */
    @Override
    synchronized public Module delete(Long moduleId) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        Module m = man.find(Module.class, moduleId);
        man.remove(m);
        man.getTransaction().commit();
        man.close();
        return m;
    }

    /**
     *
     * @param moduleId
     * @return
     * @throws PersistenceException
     */
    @Override
    public Module getModuleById(Long moduleId) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        Module m = man.find(Module.class, moduleId);
        man.getTransaction().commit();
        man.close();
        return m;
    }

    /**
     *
     * @return
     * @throws PersistenceException
     */
    @Override
    public List<Module> listAll() throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        List<Module> m = man.createQuery("Select from module m", Module.class).getResultList();
        man.getTransaction().commit();
        man.close();
        return m;
    }

    @Override
    public List<Module> getModulesSubject(long subjectId) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        List<Module> m = man.createQuery("Select m from module m where m.dominio=" + subjectId, Module.class).getResultList();
        man.getTransaction().commit();
        man.close();
        return m;
    }

    @Override
    public List<Module> searchModules(String busca) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        man.getTransaction().begin();
        List<Module> m = man.createQuery("Select m from Module m where m.nomeModulo like :searchkey", Module.class).setParameter("searchKey", MatchMode.ANYWHERE.toMatchString(busca)).getResultList();
        man.getTransaction().commit();
        man.close();
        return m;
    }
    
}
