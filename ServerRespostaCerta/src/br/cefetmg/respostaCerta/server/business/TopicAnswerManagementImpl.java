/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.server.business;

import br.cefetmg.respostaCerta.model.domain.TopicAnswer;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.dao.TopicAnswerDAO;
import br.cefetmg.respostaCerta.model.dao.TopicAnswerDAOImpl;
import br.cefetmg.respostaCerta.model.service.TopicAnswerManagement;
import java.rmi.Remote;
import java.util.List;

/**
 *
 * @author adalbs
 */
public class TopicAnswerManagementImpl implements TopicAnswerManagement{
    private final TopicAnswerManagement answerManagement;
    
    public TopicAnswerManagementImpl(){
        this.answerManagement = new br.cefetmg.respostaCerta.model.service.TopicAnswerManagementImpl(new TopicAnswerDAOImpl());
    }
    /**
     *
     * @param topicAnswer
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void registerTopicAnswer(TopicAnswer topicAnswer) throws BusinessException, PersistenceException {
        if(topicAnswer==null){
            throw new BusinessException("TopicAnswer não pode ser nulo");
        }
        if(topicAnswer.getAutor()==null){
            throw new BusinessException("Autor da resposta não pode ser nulo");
        }
        if(topicAnswer.getDataResposta()==null){
            throw new BusinessException("Data da resposta não pode ser nula");
        }
        if(topicAnswer.getTxtMensagem()==null){
            throw new BusinessException("Texto não pode ser nulo");
        }
        answerManagement.registerTopicAnswer(topicAnswer);
    }

    /**
     *
     * @param id
     * @param topicAnswer
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void updateTopicAnswer(Long id, TopicAnswer topicAnswer) throws BusinessException, PersistenceException {
        if(id == null){
            throw new BusinessException("ID não pode ser nulo");
        }
        if(topicAnswer==null){
            throw new BusinessException("TopicAnswer não pode ser nulo");
        }
        if(topicAnswer.getAutor()==null){
            throw new BusinessException("Autor da resposta não pode ser nulo");
        }
        if(topicAnswer.getDataResposta()==null){
            throw new BusinessException("Data da resposta não pode ser nula");
        }
        if(topicAnswer.getTxtMensagem()==null){
            throw new BusinessException("Texto não pode ser nulo");
        }
        answerManagement.updateTopicAnswer(id, topicAnswer);
        if(!topicAnswer.equals(answerManagement.getTopicAnswerById(id))){
            throw new PersistenceException("Erro de persistencia ao atualizar");
        }
    }

    /**
     *
     * @param id
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void removeTopicAnswer(Long id) throws BusinessException, PersistenceException {
        if(id == null){
            throw new BusinessException("ID não pode ser nulo");
        }
        answerManagement.removeTopicAnswer(id);
    }

    /**
     *
     * @param id
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public TopicAnswer getTopicAnswerById(Long id) throws BusinessException, PersistenceException {
        if(id == null){
            throw new BusinessException("ID não pode ser nulo");
        }
        return answerManagement.getTopicAnswerById(id);
        
    }
    
    /**
     *
     * @param id
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public List<TopicAnswer> getAnswersTopic(Long id) throws BusinessException, PersistenceException {
        if(id==null){
            throw new BusinessException("ID não pode ser nulo");
        }
        List <TopicAnswer> temp = answerManagement.getAnswersTopic(id);
        return temp;
    }
    
}
