/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.Question;
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
public class OpenQuestionDAOImpl implements OpenQuestionDAO{
    private static OpenQuestionDAOImpl openQuestionDAO = null;        

    private static final HashMap<Long, Question> openQuestionDB = new HashMap<>();    
    private static long openQuestionCount;
    
    /**
     *
     */
    public OpenQuestionDAOImpl() { 
        openQuestionCount = 0;
    }

    /**
     *
     * @return
     */
    public static OpenQuestionDAOImpl getInstance() {
        
        if (openQuestionDAO == null)
            openQuestionDAO = new OpenQuestionDAOImpl();
        
        return  openQuestionDAO;
    }
    
    /**
     *
     * @param openQuestion
     * @throws PersistenceException
     */
    @Override
    synchronized public void insert(Question openQuestion) throws PersistenceException {

        if (openQuestion == null)
            throw new PersistenceException("Entidade não pode ser nula.");                
        
        Long openQuestionId = openQuestion.getIdQuestao();
        
        if ((openQuestionId != null) && openQuestionDB.containsKey(openQuestionId))
            throw new PersistenceException("Duplicação de chave.");
        
        openQuestionId = ++openQuestionCount;
        openQuestion.setIdQuestao(openQuestionId);
        openQuestionDB.put(openQuestionId, openQuestion);
    }
    
    /**
     *
     * @param openQuestion
     * @throws PersistenceException
     */
    @Override
    synchronized public void update(Question openQuestion) throws PersistenceException {

        if (openQuestion == null)
            throw new PersistenceException("Entidade não pode ser nula.");              
        
        Long openQuestionId = openQuestion.getIdQuestao();

        if (openQuestionId == null)
            throw new PersistenceException("Chave da entidade não pode ser nulo.");        
        
        if (!openQuestionDB.containsKey(openQuestionId))
            throw new PersistenceException("Não existe entidade com a chave " + openQuestionId + ".");
        
        openQuestionDB.replace(openQuestionId, openQuestion);
    }

    /**
     *
     * @param openQuestionId
     * @return
     * @throws PersistenceException
     */
    @Override
    synchronized public Question delete(Long openQuestionId) throws PersistenceException {
        if (openQuestionId == null)
            throw new PersistenceException("Chave da entidade não pode ser nulo.");
        
        if (!openQuestionDB.containsKey(openQuestionId))
            throw new PersistenceException("Não existe entidade com a chave " + openQuestionId + ".");
        
        return openQuestionDB.remove(openQuestionId);
    }

    /**
     *
     * @param openQuestionId
     * @return
     * @throws PersistenceException
     */
    @Override
    public Question getOpenQuestionById(Long openQuestionId) throws PersistenceException {
        
        if (openQuestionId == null)
            throw new PersistenceException("Chave da entidade não pode ser nulo.");
        
        if (!openQuestionDB.containsKey(openQuestionId))
            throw new PersistenceException("Não existe entidade com a chave " + openQuestionId + ".");
        
        return openQuestionDB.get(openQuestionId);        
        
    }

    /**
     *
     * @return
     * @throws PersistenceException
     */
    @Override
    public List<Question> listAll() throws PersistenceException {
        List<Question> openQuestionList = new ArrayList<>();
        
        Iterator<Question> iterator = openQuestionDB.values().iterator();
	while (iterator.hasNext())
            openQuestionList.add(iterator.next());
        
        return openQuestionList;
    }

    @Override
    public List<Question> getOpenQuestionsByUser(Long userId) throws PersistenceException {
        List<Question> topicList = new ArrayList<>();
        Iterator<Question> iterator = openQuestionDB.values().iterator();
	Question item;
        while (iterator.hasNext()){
            item=iterator.next();
            if(Objects.equals(item.getCriador().getIdUsuario(), userId)){
                topicList.add(iterator.next());
            }
        }    
        return topicList;
    }
}
