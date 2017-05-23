/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.domain.TopicAnswer;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.dao.TopicAnswerDAO;
import java.util.List;

/**
 *
 * @author adalbs
 */
public class TopicAnswerManagementImpl implements TopicAnswerManagement{
    private final TopicAnswerDAO answerDAO;
    
    public TopicAnswerManagementImpl(TopicAnswerDAO answerDAO){
        this.answerDAO=answerDAO;
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
            throw new BusinessException("topicanswer não pode ser nulo");
        }
        if(topicAnswer.getAutor()==null){
            throw new BusinessException("Autor da resposta não pode ser nulo");
        }
        if(topicAnswer.getDataResposta()==null){
            throw new BusinessException("Data da resposta não pode ser nula");
        }
        if(topicAnswer.getTxtMensagem()==null){
            throw new BusinessException("tesxto não pode ser nulo");
        }
        answerDAO.insert(topicAnswer);
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
            throw new BusinessException("topicanswer não pode ser nulo");
        }
        if(topicAnswer.getAutor()==null){
            throw new BusinessException("Autor da resposta não pode ser nulo");
        }
        if(topicAnswer.getDataResposta()==null){
            throw new BusinessException("Data da resposta não pode ser nula");
        }
        if(topicAnswer.getTxtMensagem()==null){
            throw new BusinessException("tesxto não pode ser nulo");
        }
        topicAnswer.setIdMensagemResposta(id);
        answerDAO.update(topicAnswer);
        if(!topicAnswer.equals(answerDAO.getTopicAnswerById(id))){
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
        answerDAO.delete(id);
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
        return answerDAO.getTopicAnswerById(id);
        
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
        List <TopicAnswer> temp = answerDAO.getTopicAnswer(id);
        return temp;
    }
    
}
