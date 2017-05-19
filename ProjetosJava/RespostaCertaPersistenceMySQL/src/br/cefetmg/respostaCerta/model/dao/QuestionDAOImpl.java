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
public class QuestionDAOImpl implements QuestionDAO{

    /**
     *
     * @param pergunta
     * @throws PersistenceException
     */
    @Override
    public void insert(Question pergunta) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param pergunta
     * @throws PersistenceException
     */
    @Override
    public void update(Question pergunta) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param perguntId
     * @return
     * @throws PersistenceException
     */
    @Override
    public Question delete(Long perguntId) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param perguntaId
     * @return
     * @throws PersistenceException
     */
    @Override
    public Question getQuestionById(Long perguntaId) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     * @throws PersistenceException
     */
    @Override
    public List<Question> listAll() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
