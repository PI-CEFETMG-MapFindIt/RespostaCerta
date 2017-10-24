/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author umcan
 */
public interface OpenQuestionManagement extends Remote{
    public void registerQuestion(Question question) throws BusinessException, PersistenceException, RemoteException;
    public void updateQuestion(Long id, Question question) throws BusinessException, PersistenceException, RemoteException;
    public void removeQuestion(Long id) throws BusinessException, PersistenceException, RemoteException;
    public Question getQuestionById(Long id) throws BusinessException, PersistenceException, RemoteException;
    public List<Question> getQuestionsByUser(Long id) throws BusinessException, PersistenceException, RemoteException;
    public List<Question> searchQuestion(String Parameter) throws BusinessException, PersistenceException, RemoteException;
    public List<Question> getOpenQuestionByModule(Long id) throws BusinessException, PersistenceException, RemoteException;
    public List<Question> getAllQuestions() throws BusinessException, PersistenceException, RemoteException;
}
