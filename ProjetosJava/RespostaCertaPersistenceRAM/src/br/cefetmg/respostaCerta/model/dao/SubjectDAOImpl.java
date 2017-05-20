/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author umcan
 */
public class SubjectDAOImpl implements SubjectDAO{

    private static SubjectDAOImpl subjectDAO = null;        

    private static final HashMap<Long, Subject> subjectDB = new HashMap<>();    
    private static long subjectCount;
    
    /**
     *
     */
    public SubjectDAOImpl() { 
        subjectCount = 0;
    }

    /**
     *
     * @return
     */
    public static SubjectDAOImpl getInstance() {
        
        if (subjectDAO == null)
            subjectDAO = new SubjectDAOImpl();
        
        return  subjectDAO;
    }
    
    /**
     *
     * @param subject
     * @throws PersistenceException
     */
    @Override
    synchronized public void insert(Subject subject) throws PersistenceException {

        if (subject == null)
            throw new PersistenceException("Entidade não pode ser nula.");                
        
        Long subjectId = subject.getIdDominio();
        
        if ((subjectId != null) && subjectDB.containsKey(subjectId))
            throw new PersistenceException("Duplicação de chave.");
        
        subjectId = ++subjectCount;
        subject.setIdDominio(subjectId);
        subjectDB.put(subjectId, subject);
    }
    
    /**
     *
     * @param subject
     * @throws PersistenceException
     */
    @Override
    synchronized public void update(Subject subject) throws PersistenceException {

        if (subject == null)
            throw new PersistenceException("Entidade não pode ser nula.");              
        
        Long subjectId = subject.getIdDominio();

        if (subjectId == null)
            throw new PersistenceException("Chave da entidade não pode ser nulo.");        
        
        if (!subjectDB.containsKey(subjectId))
            throw new PersistenceException("Não existe entidade com a chave " + subjectId + ".");
        
        subjectDB.replace(subjectId, subject);
    }

    /**
     *
     * @param subjectId
     * @return
     * @throws PersistenceException
     */
    @Override
    synchronized public Subject delete(Long subjectId) throws PersistenceException {
        if (subjectId == null)
            throw new PersistenceException("Chave da entidade não pode ser nulo.");
        
        if (!subjectDB.containsKey(subjectId))
            throw new PersistenceException("Não existe entidade com a chave " + subjectId + ".");
        
        return subjectDB.remove(subjectId);
    }

    /**
     *
     * @param subjectId
     * @return
     * @throws PersistenceException
     */
    @Override
    public Subject getSubjectById(Long subjectId) throws PersistenceException {
        
        if (subjectId == null)
            throw new PersistenceException("Chave da entidade não pode ser nulo.");
        
        if (!subjectDB.containsKey(subjectId))
            throw new PersistenceException("Não existe entidade com a chave " + subjectId + ".");
        
        return subjectDB.get(subjectId);        
        
    }

    /**
     *
     * @return
     * @throws PersistenceException
     */
    @Override
    public List<Subject> listAll() throws PersistenceException {
        List<Subject> subjectList = new ArrayList<>();
        
        Iterator<Subject> iterator = subjectDB.values().iterator();
	while (iterator.hasNext())
            subjectList.add(iterator.next());
        
        return subjectList;
    }
    
}
