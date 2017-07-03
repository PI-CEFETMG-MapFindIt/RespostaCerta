/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.dao.ClosedAnswerDAO;
import br.cefetmg.respostaCerta.model.domain.ClosedAnswer;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author aluno
 */
public class ClosedAnswerManagementImpl implements ClosedAnswerManagement{
    private final ClosedAnswerDAO closedAnswerDAO;

    public ClosedAnswerManagementImpl(ClosedAnswerDAO closedAnswerDAO) {
        this.closedAnswerDAO = closedAnswerDAO;
    }
    /**
     *
     * @param questionAnswer
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void registerQuestionAnswer(ClosedAnswer questionAnswer) throws BusinessException, PersistenceException {
        if(questionAnswer==null){
            throw new BusinessException("A resposta não pode ser nula");
        }
        if(questionAnswer.getAutor()==null){
            throw new BusinessException("Autor não pode ser nulo");
        }
        if(questionAnswer.getDataResposta()==null){
            throw new BusinessException("Data da resposta não pode ser nula");
        }
        closedAnswerDAO.insert(questionAnswer);
        
    }

    /**
     *
     * @param id
     * @param questionAnswer
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void updateQuestionAnswer(Long id, ClosedAnswer questionAnswer) throws BusinessException, PersistenceException {
        if(questionAnswer==null){
            throw new BusinessException("A resposta não pode ser nula");
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
        closedAnswerDAO.update(questionAnswer);
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
        closedAnswerDAO.delete(id);
    }

    /**
     *
     * @param id
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public ClosedAnswer getQuestionAnswerById(Long id) throws BusinessException, PersistenceException {
        if(id==null){
            throw new BusinessException("ID não pode ser nulo");
        }
        return closedAnswerDAO.getClosedAnswerById(id);
    }

    @Override
    public List<ClosedAnswer> getAllAnswers() throws BusinessException, PersistenceException {
        return closedAnswerDAO.listAll();
    }
}
