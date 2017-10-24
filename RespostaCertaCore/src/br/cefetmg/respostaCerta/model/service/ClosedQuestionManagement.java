/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.domain.ClosedQuestion;
import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author aluno
 */
public interface ClosedQuestionManagement extends Remote{
    public void registerQuestion(ClosedQuestion question) throws BusinessException, PersistenceException, RemoteException;
    public void updateQuestion(Long id, ClosedQuestion question) throws BusinessException, PersistenceException, RemoteException;
    public void removeQuestion(Long id) throws BusinessException, PersistenceException, RemoteException;
    public ClosedQuestion getQuestionById(Long id) throws BusinessException, PersistenceException, RemoteException;
    public List<ClosedQuestion> getQuestionsByUser(Long id) throws BusinessException, PersistenceException, RemoteException;
    public List<Question> searchClosedQuestion(String Parameter) throws BusinessException, PersistenceException, RemoteException;
    public List<Question> getClosedQuestionByModule(Long id) throws BusinessException, PersistenceException, RemoteException;
    public List<ClosedQuestion> getAllQuestions() throws BusinessException, PersistenceException, RemoteException;
}
