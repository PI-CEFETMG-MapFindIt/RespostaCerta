/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.server.business;

import br.cefetmg.respostaCerta.model.domain.Topic;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.dao.TopicDAO;
import br.cefetmg.respostaCerta.model.dao.TopicDAOImpl;
import br.cefetmg.respostaCerta.model.server.TopicManagement;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author adalbs
 */
public class TopicManagementImpl implements TopicManagement{

    private final br.cefetmg.respostaCerta.model.service.TopicManagement topicManagement;

    public TopicManagementImpl() {
        this.topicManagement = new br.cefetmg.respostaCerta.model.service.TopicManagementImpl(new TopicDAOImpl());
    }
    
    
    /**
     *
     * @param topic
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void registerTopic(Topic topic) throws BusinessException, PersistenceException, RemoteException{
        if(topic == null){
            throw new BusinessException ("Topico não pode ser nulo");
        }
        if(topic.getDataPostagem() == null){
            throw new BusinessException ("Data da postagem não pode ser nulo");
        }
        if(topic.getAutor()==null){
            throw new BusinessException ("Autor não pode ser nulo");
        }
        if(topic.getTxtMensagem()==null){
            throw new BusinessException ("Texto da mensagem não pode ser nulo");
        }
        topicManagement.registerTopic(topic);
    }

    /**
     *
     * @param id
     * @param topic
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void updateTopic(Long id, Topic topic) throws BusinessException, PersistenceException, RemoteException{
        if(id==null){
            throw new BusinessException("Id não pode ser nulo");
        }
        if(topic == null){
            throw new BusinessException ("Topico não pode ser nulo");
        }
        if(topic.getDataPostagem() == null){
            throw new BusinessException ("Data da postagem não pode ser nulo");
        }
        if(topic.getAutor()==null){
            throw new BusinessException ("Autor não pode ser nulo");
        }
        if(topic.getTxtMensagem()==null){
            throw new BusinessException ("Texto da mensagem não pode ser nulo");
        }
        topicManagement.updateTopic(id, topic);
        if(!topic.equals(topicManagement.getTopicById(id))){
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
    public void removeTopic(Long id) throws BusinessException, PersistenceException, RemoteException{
        if(id==null){
            throw new BusinessException("ID não pode ser nulo");
        }
        topicManagement.removeTopic(id);
    }

    /**
     *
     * @param id
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public Topic getTopicById(Long id) throws BusinessException, PersistenceException, RemoteException{
        if(id == null){
            throw new BusinessException("ID não pode ser nulo");
        }
        return topicManagement.getTopicById(id);
    }
 
    /**
     *
     * @param id
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public List<Topic> getTopicsForum(Long id) throws BusinessException, PersistenceException, RemoteException{
        if(id==null){
            throw new BusinessException("Id do forum nao pode ser nulo");
        }
        List topics=topicManagement.getTopicsForum(id);
        return topics;
    }
    
}
