/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.domain.OpenAnswer;
import br.cefetmg.respostaCerta.model.domain.QuestionAnswer;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;

/**
 *
 * @author umcan
 */
public interface OpenAnswerManagement {
    public void registerQuestionAnswer(OpenAnswer questionAnswer) throws BusinessException, PersistenceException;
    public void updateQuestionAnswer(Long id, OpenAnswer questionAnswer) throws BusinessException, PersistenceException;
    public void removeQuestionAnswer(Long id) throws BusinessException, PersistenceException;
    public OpenAnswer getQuestionAnswerById(Long id) throws BusinessException, PersistenceException;
}
