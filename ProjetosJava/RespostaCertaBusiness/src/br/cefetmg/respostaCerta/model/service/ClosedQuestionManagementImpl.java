/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.dao.ClosedQuestionDAO;
import br.cefetmg.respostaCerta.model.domain.ClosedQuestion;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author aluno
 */
public class ClosedQuestionManagementImpl implements ClosedQuestionManagement{
    private final ClosedQuestionDAO questC;

    public ClosedQuestionManagementImpl(ClosedQuestionDAO questC) {
        this.questC = questC;
    }
    /**
     *
     * @param question
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void registerQuestion(ClosedQuestion question) throws BusinessException, PersistenceException {
        if(question==null){
            throw new BusinessException("question não pode ser nulo");
        }
        if(question.getCriador()==null){
            throw new BusinessException("criador não pode ser nulo");
        }
        if(question.getDataCriacao()==null){
            throw new BusinessException("data de criação não pode ser nulo");
        }
        if(question.getModulo()==null){
            throw new BusinessException("modulo não pode ser nulo");
        }
        if(question.getEnunciadoQuestao()==null){
            throw new BusinessException("enunciado não pode ser nulo");
        }
        if(question.getTituloQuestao()==null){
            throw new BusinessException("titulo não pode ser nulo");
        }
        if(question.getAlt1()==null || question.getAlt2()==null || question.getAlt3()==null || question.getAlt4()==null || question.getAlt5()==null){
           throw new BusinessException("alternativas não podem ser nulas"); 
        }
        questC.insert(question);
    }

    /**
     *
     * @param id
     * @param question
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void updateQuestion(Long id, ClosedQuestion question) throws BusinessException, PersistenceException {
        if(question==null){
            throw new BusinessException("question não pode ser nulo");
        }
        if(question.getCriador()==null){
            throw new BusinessException("criador não pode ser nulo");
        }
        if(question.getDataCriacao()==null){
            throw new BusinessException("data de criação não pode ser nulo");
        }
        if(question.getDominio()==null){
            throw new BusinessException("dominio não pode ser nulo");
        }
        if(question.getEnunciadoQuestao()==null){
            throw new BusinessException("enunciado não pode ser nulo");
        }
        if(question.getTituloQuestao()==null){
            throw new BusinessException("titulo não pode ser nulo");
        }
        if(id==null){
            throw new BusinessException("Id não pode ser nulo");
        }
        question.setIdQuestao(id);
        questC.update(question);   
    }

    /**
     *
     * @param id
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void removeQuestion(Long id) throws BusinessException, PersistenceException {
        if(id==null){
            throw new BusinessException("Id não pode ser nulo");
        }
        questC.delete(id);
    }

    /**
     *
     * @param id
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public ClosedQuestion getQuestionById(Long id) throws BusinessException, PersistenceException {
        if(id==null){
            throw new BusinessException("Id não pode ser nulo");
        }
        return questC.getClosedQuestionById(id);
    }

    @Override
    public List<ClosedQuestion> getQuestionsByUser(Long id) throws BusinessException, PersistenceException {
        if(id==null){
            throw new BusinessException("Id não pode ser nulo");
        }
        List<ClosedQuestion> resp=questC.getClosedQuestionsByUser(id);
        return resp;
    }
}
