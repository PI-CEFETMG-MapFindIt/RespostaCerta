/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.domain.ClosedQuestion;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;

/**
 *
 * @author umcan
 */
public interface QuestionManagement {
    public Long registerQuestion(Question question) throws BusinessException, PersistenceException;
    public void updateQuestion(Long id, Question question) throws BusinessException, PersistenceException;
    public void removeOpenQuestion(Long id) throws BusinessException, PersistenceException;
    public Question getOpenQuestionById(Long id) throws BusinessException, PersistenceException;
    public void removeClosedQuestion(Long id) throws BusinessException, PersistenceException;
    public ClosedQuestion getClosedQuestionById(Long id) throws BusinessException, PersistenceException;
}
