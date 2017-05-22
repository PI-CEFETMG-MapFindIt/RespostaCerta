/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.dao.ModuleDAO;
import br.cefetmg.respostaCerta.model.dao.ClosedAnswerDAO;
import br.cefetmg.respostaCerta.model.dao.SubjectDAO;
/**
 *
 * @author adalbs
 */
public class PerformanceManagementImpl implements PerformanceManagement{
    
    
    private final ClosedAnswerDAO answer;
    private final ModuleDAO module;
    private final SubjectDAO subject;

    public PerformanceManagementImpl(ClosedAnswerDAO answer, ModuleDAO module, SubjectDAO subject) {
        this.answer = answer;
        this.module = module;
        this.subject = subject;
    }
    
    
    
    /**
     *
     * @param user
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public Double calculateErrors(User user) throws BusinessException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param user
     * @param modulo
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public Double calculateErrorsByModule(User user, Module modulo) throws BusinessException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param user
     * @param disciplina
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public Double calculateErrosBySubject(User user, Subject disciplina) throws BusinessException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
