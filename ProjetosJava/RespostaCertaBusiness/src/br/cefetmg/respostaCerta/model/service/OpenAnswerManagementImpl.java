/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.dao.OpenAnswerDAO;
import br.cefetmg.respostaCerta.model.domain.OpenAnswer;
/**
 *
 * @author adalbs
 */
public class OpenAnswerManagementImpl implements OpenAnswerManagement{
    private final OpenAnswerDAO openAnswerDAO;

    public OpenAnswerManagementImpl(OpenAnswerDAO openAnswerDAO) {
        this.openAnswerDAO = openAnswerDAO;
    }
    /**
     *
     * @param questionAnswer
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void registerQuestionAnswer(OpenAnswer questionAnswer) throws BusinessException, PersistenceException {
        if(questionAnswer==null){
            throw new BusinessException("A questão não pode ser nulo");
        }
        if(questionAnswer.getAutor()==null){
            throw new BusinessException("Autor não pode ser nulo");
        }
        if(questionAnswer.getDataResposta()==null){
            throw new BusinessException("Data da resposta não pode ser nula");
        }
        openAnswerDAO.insert(questionAnswer);
        
    }

    /**
     *
     * @param id
     * @param questionAnswer
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void updateQuestionAnswer(Long id, OpenAnswer questionAnswer) throws BusinessException, PersistenceException {
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
        openAnswerDAO.update(questionAnswer);
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
        openAnswerDAO.delete(id);
    }

    /**
     *
     * @param id
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public OpenAnswer getQuestionAnswerById(Long id) throws BusinessException, PersistenceException {
        if(id==null){
            throw new BusinessException("ID não pode ser nulo");
        }
        return openAnswerDAO.getOpenAnswerById(id);
    }
    
}
