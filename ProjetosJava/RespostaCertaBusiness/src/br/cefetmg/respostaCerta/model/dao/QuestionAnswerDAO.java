/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.QuestionAnswer;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author umcan
 */
public interface QuestionAnswerDAO {
    public void insert(QuestionAnswer resposta) throws PersistenceException;
    public void update(QuestionAnswer resposta) throws PersistenceException;
    public QuestionAnswer delete(Long respostaId) throws PersistenceException;
    public QuestionAnswer getQuestionAnswerById(Long respostaId) throws PersistenceException;
    public List<QuestionAnswer> listAll() throws PersistenceException;
}
