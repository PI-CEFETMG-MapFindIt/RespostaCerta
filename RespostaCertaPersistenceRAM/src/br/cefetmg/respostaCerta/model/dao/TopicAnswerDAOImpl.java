/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.TopicAnswer;
import br.cefetmg.respostaCerta.model.domain.TopicAnswer;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author umcan
 */
public class TopicAnswerDAOImpl implements TopicAnswerDAO{

    private static TopicAnswerDAOImpl topicAnswerDAO = null;        

    private static final HashMap<Long, TopicAnswer> topicAnswerDB = new HashMap<>();    
    private static long topicAnswerCount;
    
    /**
     *
     */
    public TopicAnswerDAOImpl() { 
        topicAnswerCount = 0;
    }

    /**
     *
     * @return
     */
    public static TopicAnswerDAOImpl getInstance() {
        
        if (topicAnswerDAO == null)
            topicAnswerDAO = new TopicAnswerDAOImpl();
        
        return  topicAnswerDAO;
    }
    
    /**
     *
     * @param topicAnswer
     * @throws PersistenceException
     */
    @Override
    synchronized public void insert(TopicAnswer topicAnswer) throws PersistenceException {

        if (topicAnswer == null)
            throw new PersistenceException("Entidade não pode ser nula.");                
        
        Long topicAnswerId = topicAnswer.getIdMensagemResposta();
        
        if ((topicAnswerId != null) && topicAnswerDB.containsKey(topicAnswerId))
            throw new PersistenceException("Duplicação de chave.");
        
        topicAnswerId = ++topicAnswerCount;
        topicAnswer.setIdMensagemResposta(topicAnswerCount);
        topicAnswerDB.put(topicAnswerId, topicAnswer);
    }
    
    /**
     *
     * @param topicAnswer
     * @throws PersistenceException
     */
    @Override
    synchronized public void update(TopicAnswer topicAnswer) throws PersistenceException {

        if (topicAnswer == null)
            throw new PersistenceException("Entidade não pode ser nula.");              
        
        Long topicAnswerId = topicAnswer.getIdMensagemResposta();

        if (topicAnswerId == null)
            throw new PersistenceException("Chave da entidade não pode ser nulo.");        
        
        if (!topicAnswerDB.containsKey(topicAnswerId))
            throw new PersistenceException("Não existe entidade com a chave " + topicAnswerId + ".");
        
        topicAnswerDB.replace(topicAnswerId, topicAnswer);
    }

    /**
     *
     * @param topicAnswerId
     * @return
     * @throws PersistenceException
     */
    @Override
    synchronized public TopicAnswer delete(Long topicAnswerId) throws PersistenceException {
        if (topicAnswerId == null)
            throw new PersistenceException("Chave da entidade não pode ser nulo.");
        
        if (!topicAnswerDB.containsKey(topicAnswerId))
            throw new PersistenceException("Não existe entidade com a chave " + topicAnswerId + ".");
        
        return topicAnswerDB.remove(topicAnswerId);
    }

    /**
     *
     * @param topicAnswerId
     * @return
     * @throws PersistenceException
     */
    @Override
    public TopicAnswer getTopicAnswerById(Long topicAnswerId) throws PersistenceException {
        
        if (topicAnswerId == null)
            throw new PersistenceException("Chave da entidade não pode ser nulo.");
        
        if (!topicAnswerDB.containsKey(topicAnswerId))
            throw new PersistenceException("Não existe entidade com a chave " + topicAnswerId + ".");
        
        return topicAnswerDB.get(topicAnswerId);        
        
    }

    /**
     *
     * @return
     * @throws PersistenceException
     */
    @Override
    public List<TopicAnswer> listAll() throws PersistenceException {
        List<TopicAnswer> topicAnswerList = new ArrayList<>();
        
        Iterator<TopicAnswer> iterator = topicAnswerDB.values().iterator();
	while (iterator.hasNext())
            topicAnswerList.add(iterator.next());
        
        return topicAnswerList;
    }
    
}
