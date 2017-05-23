/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.ClosedAnswer;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author umcan
 */
public class ClosedAnswerDAOImpl implements ClosedAnswerDAO{
    private static ClosedAnswerDAOImpl closedDAO = null;        

    private static final HashMap<Long, ClosedAnswer> closedAnswerDB = new HashMap<>();    
    private static long closedCount;
    
    public ClosedAnswerDAOImpl() { 
        closedCount = 0;
    }

    /**
     *
     * @return
     */
    public static ClosedAnswerDAOImpl getInstance() {
        
        if (closedDAO == null)
            closedDAO = new ClosedAnswerDAOImpl();
        
        return  closedDAO;
    }
    
    /**
     *
     * @param respostaFechada
     * @throws PersistenceException
     */
    @Override
    synchronized public void insert(ClosedAnswer respostaFechada) throws PersistenceException {

        if (respostaFechada == null)
            throw new PersistenceException("Entidade não pode ser nula.");                
        
        Long respId = respostaFechada.getIdResposta();
        
        if ((respId != null) && closedAnswerDB.containsKey(respId))
            throw new PersistenceException("Duplicação de chave.");
        
        respId = ++closedCount;
        respostaFechada.setIdResposta(respId);
        closedAnswerDB.put(respId, respostaFechada);
    }
    
    /**
     *
     * @param closedAnswer
     * @throws PersistenceException
     */
    @Override
    synchronized public void update(ClosedAnswer closedAnswer) throws PersistenceException {

        if (closedAnswer == null)
            throw new PersistenceException("Entidade não pode ser nula.");              
        
        Long closedId = closedAnswer.getIdResposta();

        if (closedId == null)
            throw new PersistenceException("Chave da entidade não pode ser nulo.");        
        
        if (!closedAnswerDB.containsKey(closedId))
            throw new PersistenceException("Não existe entidade com a chave " + closedId + ".");
        
        closedAnswerDB.replace(closedId, closedAnswer);
    }

    /**
     *
     * @param closedId
     * @return
     * @throws PersistenceException
     */
    @Override
    synchronized public ClosedAnswer delete(Long closedId) throws PersistenceException {
        if (closedId == null)
            throw new PersistenceException("Chave da entidade não pode ser nulo.");
        
        if (!closedAnswerDB.containsKey(closedId))
            throw new PersistenceException("Não existe entidade com a chave " + closedId + ".");
        
        return closedAnswerDB.remove(closedId);
    }

    /**
     *
     * @param closedId
     * @return
     * @throws PersistenceException
     */
    @Override
    public ClosedAnswer getClosedAnswerById(Long closedId) throws PersistenceException {
        
        if (closedId == null)
            throw new PersistenceException("Chave da entidade não pode ser nulo.");
        
        if (!closedAnswerDB.containsKey(closedId))
            throw new PersistenceException("Não existe entidade com a chave " + closedId + ".");
        
        return closedAnswerDB.get(closedId);        
        
    }

    /**
     *
     * @return
     * @throws PersistenceException
     */
    @Override
    public List<ClosedAnswer> listAll() throws PersistenceException {
        List<ClosedAnswer> closedList = new ArrayList<>();
        
        Iterator<ClosedAnswer> iterator = closedAnswerDB.values().iterator();
	while (iterator.hasNext())
            closedList.add(iterator.next());
        
        return closedList;
    }    

    @Override
    public List<ClosedAnswer> getClosedAnswerByUser(Long userId) throws PersistenceException {
        List<ClosedAnswer> closedList = new ArrayList<>();
        Iterator<ClosedAnswer> iterator = closedAnswerDB.values().iterator();
	ClosedAnswer item;
        while (iterator.hasNext()){
            item=iterator.next();
            if(item.getAutor().getIdUsuario()==userId){
                closedList.add(iterator.next());
            }
            
        }
            
        
        return closedList;
    }
    
}
