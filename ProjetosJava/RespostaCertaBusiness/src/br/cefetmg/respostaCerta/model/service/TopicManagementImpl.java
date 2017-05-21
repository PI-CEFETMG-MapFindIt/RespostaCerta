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
    public Long registerTopic(Topic topic) throws BusinessException, PersistenceException {
        if(topic.getDataPostagem() == null || topic.getAutor()==null || topic.getTxtMensagem()==null){
            throw new BusinessException ("Campos não podem ser nulos");
        }
        topicDAO.insert(topic);
        if(topicDAO.getTopicById(topic.getIdMensagem())!=topic){
            throw new PersistenceException("Erro ao adicionar mensagem");
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        topic.setIdMensagem(id);
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
    
}
