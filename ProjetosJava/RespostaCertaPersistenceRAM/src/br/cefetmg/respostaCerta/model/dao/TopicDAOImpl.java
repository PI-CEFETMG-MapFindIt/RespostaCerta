/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.Topic;
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
public class TopicDAOImpl implements TopicDAO{

    private static TopicDAOImpl topicDAO = null;        

    private static final HashMap<Long, Topic> topicDB = new HashMap<>();    
    private static long topicCount;
    
    /**
     *
     */
    public TopicDAOImpl() { 
        topicCount = 0;
    }

    /**
     *
     * @return
     */
    public static TopicDAOImpl getInstance() {
        
        if (topicDAO == null)
            topicDAO = new TopicDAOImpl();
        
        return  topicDAO;
    }
    
    /**
     *
     * @param topic
     * @throws PersistenceException
     */
    @Override
    synchronized public void insert(Topic topic) throws PersistenceException {

        if (topic == null)
            throw new PersistenceException("Entidade não pode ser nula.");                
        
        Long topicId = topic.getTopicoId();
        
        if ((topicId != null) && topicDB.containsKey(topicId))
            throw new PersistenceException("Duplicação de chave.");
        
        topicId = ++topicCount;
        topic.setTopicoId(topicId);
        topicDB.put(topicId, topic);
    }
    
    /**
     *
     * @param topic
     * @throws PersistenceException
     */
    @Override
    synchronized public void update(Topic topic) throws PersistenceException {

        if (topic == null)
            throw new PersistenceException("Entidade não pode ser nula.");              
        
        Long topicId = topic.getTopicoId();

        if (topicId == null)
            throw new PersistenceException("Chave da entidade não pode ser nulo.");        
        
        if (!topicDB.containsKey(topicId))
            throw new PersistenceException("Não existe entidade com a chave " + topicId + ".");
        
        topicDB.replace(topicId, topic);
    }

    /**
     *
     * @param topicId
     * @return
     * @throws PersistenceException
     */
    @Override
    synchronized public Topic delete(Long topicId) throws PersistenceException {
        if (topicId == null)
            throw new PersistenceException("Chave da entidade não pode ser nulo.");
        
        if (!topicDB.containsKey(topicId))
            throw new PersistenceException("Não existe entidade com a chave " + topicId + ".");
        
        return topicDB.remove(topicId);
    }

    /**
     *
     * @param topicId
     * @return
     * @throws PersistenceException
     */
    @Override
    public Topic getTopicById(Long topicId) throws PersistenceException {
        
        if (topicId == null)
            throw new PersistenceException("Chave da entidade não pode ser nulo.");
        
        if (!topicDB.containsKey(topicId))
            throw new PersistenceException("Não existe entidade com a chave " + topicId + ".");
        
        return topicDB.get(topicId);        
        
    }

    /**
     *
     * @return
     * @throws PersistenceException
     */
    @Override
    public List<Topic> listAll() throws PersistenceException {
        List<Topic> topicList = new ArrayList<>();
        
        Iterator<Topic> iterator = topicDB.values().iterator();
	while (iterator.hasNext())
            topicList.add(iterator.next());
        
        return topicList;
    }
    
    
    @Override
    public List<Topic> getForumTopic(Long forumID) throws PersistenceException {
        List<Topic> topicList = new ArrayList<>();
        Iterator<Topic> iterator = topicDB.values().iterator();
	Topic item;
        while (iterator.hasNext()){
            item=iterator.next();
            if(Objects.equals(item.getForum().getIdForum(), forumID)){
                topicList.add(item);
            }
        }    
        return topicList;
    }
    
}
