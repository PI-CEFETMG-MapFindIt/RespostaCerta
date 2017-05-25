/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.domain.Topic;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.dao.TopicDAO;
import br.cefetmg.respostaCerta.model.domain.TopicAnswer;
import java.util.List;

/**
 *
 * @author adalbs
 */
public class TopicManagementImpl implements TopicManagement{

    private final TopicDAO topicDAO;

    public TopicManagementImpl(TopicDAO topicDAO) {
        this.topicDAO = topicDAO;
    }
    
    
    /**
     *
     * @param topic
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void registerTopic(Topic topic) throws BusinessException, PersistenceException {
        if(topic == null){
            throw new BusinessException ("topico não pode ser nulo");
        }
        if(topic.getDataPostagem() == null){
            throw new BusinessException ("Data da postagem não pode ser nulo");
        }
        if(topic.getAutor()==null){
            throw new BusinessException ("Autor não pode ser nulo");
        }
        if(topic.getTxtMensagem()==null){
            throw new BusinessException ("texto da mensagem não pode ser nulo");
        }
        topicDAO.insert(topic);
    }

    /**
     *
     * @param id
     * @param topic
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void updateTopic(Long id, Topic topic) throws BusinessException, PersistenceException {
        if(id==null){
            throw new BusinessException("Id não pode ser nulo");
        }
        if(topic == null){
            throw new BusinessException ("topico não pode ser nulo");
        }
        if(topic.getDataPostagem() == null){
            throw new BusinessException ("Data da postagem não pode ser nulo");
        }
        if(topic.getAutor()==null){
            throw new BusinessException ("Autor não pode ser nulo");
        }
        if(topic.getTxtMensagem()==null){
            throw new BusinessException ("texto da mensagem não pode ser nulo");
        }
        topic.setTopicoId(id);
        topicDAO.update(topic);
        if(!topic.equals(topicDAO.getTopicById(id))){
            throw new PersistenceException("Erro de persistencia");
        }
    }

    /**
     *
     * @param id
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void removeTopic(Long id) throws BusinessException, PersistenceException {
        if(id==null){
            throw new BusinessException("ID não pode ser nulo");
        }
        topicDAO.delete(id);
    }

    /**
     *
     * @param id
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public Topic getTopicById(Long id) throws BusinessException, PersistenceException {
        if(id == null){
            throw new BusinessException("ID não pode ser nulo");
        }
        return topicDAO.getTopicById(id);
    }
 
    /**
     *
     * @param id
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public List<Topic> getTopicsForum(Long id) throws BusinessException, PersistenceException {
        if(id==null){
            throw new BusinessException("Id do forum nao pode ser nulo");
        }
        List topics=topicDAO.getForumTopic(id);
        return topics;
    }
    
}
