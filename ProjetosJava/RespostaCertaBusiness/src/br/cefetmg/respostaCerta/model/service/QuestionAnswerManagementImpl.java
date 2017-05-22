/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.domain.QuestionAnswer;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.dao.ClosedAnswerDAO;
import br.cefetmg.respostaCerta.model.domain.ClosedAnswer;
/**
 *
 * @author adalbs
 */
public class QuestionAnswerManagementImpl implements QuestionAnswerManagement{
    private final ClosedAnswerDAO answerDAO;

    public QuestionAnswerManagementImpl(ClosedAnswerDAO answerDAO) {
        this.answerDAO = answerDAO;
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
        if(questionAnswer==null){
            throw new BusinessException("A questão não pode ser nulo");
        }
        if(questionAnswer.getAutor()==null){
            throw new BusinessException("Autor não pode ser nulo");
        }
        if(questionAnswer.getDataResposta()==null){
            throw new BusinessException("Data da resposta não pode ser nula");
        }
        answerDAO.insert((ClosedAnswer) questionAnswer);
        return questionAnswer.getIdResposta();
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
        if(questionAnswer==null){
            throw new BusinessException("A questão não pode ser nulo");
        }
        if(questionAnswer.getAutor()==null){
            throw new BusinessException("Autor não pode ser nulo");
        }
        if(questionAnswer.getDataResposta()==null){
            throw new BusinessException("Data da resposta não pode ser nula");
        }
        if(id==null){
            throw new BusinessException("ID não pode ser nulo");
        }
        questionAnswer.setIdResposta(id);
        answerDAO.update((ClosedAnswer) questionAnswer);
    }

    /**
     *
     * @param id
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void removeQuestionAnswer(Long id) throws BusinessException, PersistenceException {
        if(id==null){
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
    public QuestionAnswer getQuestionAnswerById(Long id) throws BusinessException, PersistenceException {
        if(id==null){
            throw new BusinessException("ID não pode ser nulo");
        }
        return answerDAO.getClosedAnswerById(id);
    }
    
}
