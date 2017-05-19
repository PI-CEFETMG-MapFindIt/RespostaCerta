/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.domain.QuestionAnswer;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.dao.QuestionAnswerDAO;
/**
 *
 * @author umcan
 */
public class QuestionAnswerManagementImpl implements QuestionAnswerManagement{
    private final QuestionAnswerDAO questionA;
    
    public QuestionAnswerManagementImpl (QuestionAnswerDAO questionA){
        this.questionA = questionA;
    }
    /**
     *
     * @param questionAnswer
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public Long registerQuestionAnswer(QuestionAnswer questionAnswer) throws BusinessException, PersistenceException {
        questionA.insert(questionAnswer);
        return questionA.getQuestionAnswerById(questionAnswer.getIdResposta()).getIdResposta();
    }

    /**
     *
     * @param id
     * @param questionAnswer
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void updateQuestionAnswer(Long id, QuestionAnswer questionAnswer) throws BusinessException, PersistenceException {
        questionAnswer.setIdResposta(id);
        questionA.update(questionAnswer);
    }

    /**
     *
     * @param id
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void removeQuestionAnswer(Long id) throws BusinessException, PersistenceException {
        questionA.delete(id);
    }

    /**
     *
     * @param id
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public QuestionAnswer getQuestionAnswerById(Long id) throws BusinessException, PersistenceException {
        return questionA.getQuestionAnswerById(id);
    }
    
}
