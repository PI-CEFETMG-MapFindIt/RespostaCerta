/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.dao.ClosedQuestionDAO;
import br.cefetmg.respostaCerta.model.dao.OpenQuestionDAO;
import br.cefetmg.respostaCerta.model.domain.ClosedQuestion;
/**
 *
 * @author adalbs
 */
public class QuestionManagementImpl implements QuestionManagement{
    
    private final ClosedQuestionDAO questC;
    private final OpenQuestionDAO questO;

    public QuestionManagementImpl(ClosedQuestionDAO questC, OpenQuestionDAO questO) {
        this.questC = questC;
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
    public Long registerQuestion(Question question) throws BusinessException, PersistenceException {
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
        if(question instanceof ClosedQuestion){
            questC.insert((ClosedQuestion) question);
        }else{
            questO.insert(question);
        }
        return question.getIdQuestao();
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
        if(question instanceof ClosedQuestion){
            questC.update((ClosedQuestion) question);
            if(questC.getClosedQuestionById(id)!=question){
                throw new PersistenceException("Erro ao atualizar");
            }
        }else{
            questO.update(question);
            if(questO.getOpenQuestionById(id)!=question){
                throw new PersistenceException("Erro ao atualizar");
            }
        }     
    }

    /**
     *
     * @param id
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void removeOpenQuestion(Long id) throws BusinessException, PersistenceException {
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
    public Question getOpenQuestionById(Long id) throws BusinessException, PersistenceException {
        if(id==null){
            throw new BusinessException("Id não pode ser nulo");
        }
        return questO.getOpenQuestionById(id);
    }
    
    @Override
    public void removeClosedQuestion(Long id) throws BusinessException, PersistenceException {
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
    public ClosedQuestion getClosedQuestionById(Long id) throws BusinessException, PersistenceException {
        if(id==null){
            throw new BusinessException("Id não pode ser nulo");
        }
        return questC.getClosedQuestionById(id);
    }
    
}
