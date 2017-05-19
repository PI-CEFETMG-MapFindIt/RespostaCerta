/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.OpenAnswer;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author umcan
 */
public interface OpenAnswerDAO {
    public void insert(OpenAnswer respostaAberta) throws PersistenceException;
    public void update(OpenAnswer respostaAberta) throws PersistenceException;
    public OpenAnswer delete(Long questaoId) throws PersistenceException;
    public OpenAnswer getOpenAnswerById(Long questaoId) throws PersistenceException;
    public List<OpenAnswer> listAll() throws PersistenceException;
}
