/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.ClosedAnswer;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author umcan
 */
public interface ClosedAnswerDAO {
    public void insert(ClosedAnswer respostaFechada) throws PersistenceException;
    public void update(ClosedAnswer respostaFechada) throws PersistenceException;
    public ClosedAnswer delete(Long respostaId) throws PersistenceException;
    public ClosedAnswer getClosedAnswerById(Long respostaId) throws PersistenceException;
    public List<ClosedAnswer> listAll() throws PersistenceException;
    public List<ClosedAnswer> getClosedAnswerByUser(Long userId) throws PersistenceException;
}
