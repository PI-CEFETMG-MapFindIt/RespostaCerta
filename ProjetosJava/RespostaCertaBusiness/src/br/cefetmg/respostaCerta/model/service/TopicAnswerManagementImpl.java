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
    private final TopicAnswerDAO answearDAO;
    
    public TopicAnswerManagementImpl(TopicAnswerDAO answerDAO){
        this.answearDAO=answerDAO;
    }
    /**
     *
     * @param topicAnswer
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public Long registerTopic(TopicAnswer topicAnswer) throws BusinessException, PersistenceException {
        if(topicAnswer.getAutor()==null || topicAnswer.getDataResposta()==null||topicAnswer.getTxtMensagem()==null){
            throw new BusinessException("Campos de resposta não podem ser nulo");
        }
        answearDAO.insert(topicAnswer);
        if(answearDAO.getTopicAnswerById(topicAnswer.getIdMensagemResposta())!=null){
            return topicAnswer.getIdMensagemResposta();
        }
        throw new PersistenceException("Erro ao incerrir");
    }

    /**
     *
     * @param id
     * @param topicAnswer
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void updateTopic(Long id, TopicAnswer topicAnswer) throws BusinessException, PersistenceException {
        if(id == null){
            throw new BusinessException("ID não pode ser nulo");
        }
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param id
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void removeTopic(Long id) throws BusinessException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param id
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public TopicAnswer getTopicById(Long id) throws BusinessException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param id
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public List<TopicAnswer> getTopicAnswers(Long id) throws BusinessException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
