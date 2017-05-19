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
public class OpenAnswerDAOImpl implements OpenAnswerDAO{

    /**
     *
     * @param respostaAberta
     * @throws PersistenceException
     */
    @Override
    public void insert(OpenAnswer respostaAberta) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param respostaAberta
     * @throws PersistenceException
     */
    @Override
    public void update(OpenAnswer respostaAberta) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param questaoId
     * @return
     * @throws PersistenceException
     */
    @Override
    public OpenAnswer delete(Long questaoId) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param questaoId
     * @return
     * @throws PersistenceException
     */
    @Override
    public OpenAnswer getOpenAnswerById(Long questaoId) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     * @throws PersistenceException
     */
    @Override
    public List<OpenAnswer> listAll() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
