/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.Forum;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author umcan
 */
public interface OpenQuestionDAO {
    public void insert(Question openQuestion) throws PersistenceException;
    public void update(Question openQuestion) throws PersistenceException;
    public Question delete(Long openQuestionId) throws PersistenceException;
    public Question  getOpenQuestionById(Long openQuestionId) throws PersistenceException;
    public List<Question> listAll() throws PersistenceException;
    public List<Question> getOpenQuestionsByUser(Long userId) throws PersistenceException;
}
