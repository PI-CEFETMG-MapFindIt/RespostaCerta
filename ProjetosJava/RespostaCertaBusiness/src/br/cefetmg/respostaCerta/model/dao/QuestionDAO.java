/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author umcan
 */
public interface QuestionDAO {
    public void insert(Question pergunta) throws PersistenceException;
    public void update(Question pergunta) throws PersistenceException;
    public Question delete(Long perguntId) throws PersistenceException;
    public Question getQuestionById(Long perguntaId) throws PersistenceException;
    public List<Question> listAll() throws PersistenceException;
}
