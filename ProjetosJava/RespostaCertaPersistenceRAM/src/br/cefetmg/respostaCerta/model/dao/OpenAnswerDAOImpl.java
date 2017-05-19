/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.OpenAnswer;
import br.cefetmg.respostaCerta.model.domain.OpenAnswer;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author umcan
 */
public class OpenAnswerDAOImpl implements OpenAnswerDAO{

    private static OpenAnswerDAOImpl openAnswerDAO = null;        

    private static final HashMap<Long, OpenAnswer> openAnswerDB = new HashMap<>();    
    private static long openAnswerCount;
    
    public OpenAnswerDAOImpl() { 
        openAnswerCount = 0;
    }

    public static OpenAnswerDAOImpl getInstance() {
        
        if (openAnswerDAO == null)
            openAnswerDAO = new OpenAnswerDAOImpl();
        
        return  openAnswerDAO;
    }
    
    @Override
    synchronized public void insert(OpenAnswer openAnswer) throws PersistenceException {

        if (openAnswer == null)
            throw new PersistenceException("Entidade não pode ser nula.");                
        
        Long openAnswerId = openAnswer.getIdResposta();
        
        if ((openAnswerId != null) && openAnswerDB.containsKey(openAnswerId))
            throw new PersistenceException("Duplicação de chave.");
        
        openAnswerId = ++openAnswerCount;
        openAnswer.setIdResposta(openAnswerId);
        openAnswerDB.put(openAnswerId, openAnswer);
    }
    
    @Override
    synchronized public void update(OpenAnswer openAnswer) throws PersistenceException {

        if (openAnswer == null)
            throw new PersistenceException("Entidade não pode ser nula.");              
        
        Long openAnswerId = openAnswer.getIdResposta();

        if (openAnswerId == null)
            throw new PersistenceException("Chave da entidade não pode ser nulo.");        
        
        if (!openAnswerDB.containsKey(openAnswerId))
            throw new PersistenceException("Não existe entidade com a chave " + openAnswerId + ".");
        
        openAnswerDB.replace(openAnswerId, openAnswer);
    }

    @Override
    synchronized public OpenAnswer delete(Long openAnswerId) throws PersistenceException {
        if (openAnswerId == null)
            throw new PersistenceException("Chave da entidade não pode ser nulo.");
        
        if (!openAnswerDB.containsKey(openAnswerId))
            throw new PersistenceException("Não existe entidade com a chave " + openAnswerId + ".");
        
        return openAnswerDB.remove(openAnswerId);
    }

    @Override
    public OpenAnswer getOpenAnswerById(Long openAnswerId) throws PersistenceException {
        
        if (openAnswerId == null)
            throw new PersistenceException("Chave da entidade não pode ser nulo.");
        
        if (!openAnswerDB.containsKey(openAnswerId))
            throw new PersistenceException("Não existe entidade com a chave " + openAnswerId + ".");
        
        return openAnswerDB.get(openAnswerId);        
        
    }

    @Override
    public List<OpenAnswer> listAll() throws PersistenceException {
        List<OpenAnswer> openAnswerList = new ArrayList<>();
        
        Iterator<OpenAnswer> iterator = openAnswerDB.values().iterator();
	while (iterator.hasNext())
            openAnswerList.add(iterator.next());
        
        return openAnswerList;
    }
    
}
