/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.util.db.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        factory = Persistence.createEntityManagerFactory("Module");
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
        man.persist(module);
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
        man.merge(module);
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
        Module m = man.find(Module.class, moduleId);
        man.remove(m);
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
        Module m = man.find(Module.class, moduleId);
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
        List<Module> m = man.createQuery("from module").getResultList();
        man.close();
        return m;
    }

    @Override
    public List<Module> getModulesSubject(long subjectId) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        List<Module> m = man.createQuery("from module m where m.dominio=" + subjectId).getResultList();
        man.close();
        return m;
    }

    @Override
    public List<Module> searchModules(String busca) throws PersistenceException {
        EntityManager man = factory.createEntityManager();
        List<Module> m = man.createQuery("from ClosedQuestion m where m.nomeModulo like :searchkey").setParameter("searchKey", MatchMode.ANYWHERE.toMatchString(busca)).getResultList();
        man.close();
        return m;
    }
    
}
