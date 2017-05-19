/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.dao;

import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.util.List;

/**
 *
 * @author umcan
 */
public class SubjectDAOImpl implements SubjectDAO{

    /**
     *
     * @param disciplina
     * @throws PersistenceException
     */
    @Override
    public void insert(Subject disciplina) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param disciplina
     * @throws PersistenceException
     */
    @Override
    public void update(Subject disciplina) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param disciplinaId
     * @return
     * @throws PersistenceException
     */
    @Override
    public Subject delete(Long disciplinaId) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param disciplinaId
     * @return
     * @throws PersistenceException
     */
    @Override
    public Subject getSubjectById(Long disciplinaId) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     * @throws PersistenceException
     */
    @Override
    public List<Subject> listAll() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
