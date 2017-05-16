/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.ClosedQuestion;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author umcan
 */
public interface ClosedQuestionDAO {
    public void insert(ClosedQuestion questaoFechada) throws PersistenceException;
    public void update(ClosedQuestion questaoFechada) throws PersistenceException;
    public ClosedQuestion delete(Long questaoId) throws PersistenceException;
    public ClosedQuestion getClosedQuestionById(Long questaoId) throws PersistenceException;
    public List<ClosedQuestion> listAll() throws PersistenceException;
}
