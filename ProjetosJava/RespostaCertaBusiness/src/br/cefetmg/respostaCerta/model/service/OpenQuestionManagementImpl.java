/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.dao.OpenQuestionDAO;
import java.util.List;
/**
 *
 * @author adalbs
 */
public class OpenQuestionManagementImpl implements OpenQuestionManagement{
    
    private final OpenQuestionDAO questO;

    public OpenQuestionManagementImpl(OpenQuestionDAO questO) {
        this.questO = questO;
    }
    /**
     *
     * @param question
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void registerQuestion(Question question) throws BusinessException, PersistenceException {
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
        questO.insert(question);
    }

    /**
     *
     * @param id
     * @param question
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void updateQuestion(Long id, Question question) throws BusinessException, PersistenceException {
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
        questO.update(question);   
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
        questO.delete(id);
    }

    /**
     *
     * @param id
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public Question getQuestionById(Long id) throws BusinessException, PersistenceException {
        if(id==null){
            throw new BusinessException("Id não pode ser nulo");
        }
        return questO.getOpenQuestionById(id);
    }

    @Override
    public List<Question> getQuestionsByUser(Long id) throws BusinessException, PersistenceException {
        if(id==null){
            throw new BusinessException("Id não pode ser nulo");
        }
        List<Question> resp=questO.getOpenQuestionsByUser(id);
        return resp;
    }
    
}
