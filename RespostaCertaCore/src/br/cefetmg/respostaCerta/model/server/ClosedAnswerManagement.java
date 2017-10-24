/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.server;

import br.cefetmg.respostaCerta.model.service.*;
import br.cefetmg.respostaCerta.model.domain.ClosedAnswer;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author aluno
 */
public interface ClosedAnswerManagement extends Remote{
    public void registerQuestionAnswer(ClosedAnswer questionAnswer) throws BusinessException, PersistenceException, RemoteException;
    public void updateQuestionAnswer(Long id, ClosedAnswer questionAnswer) throws BusinessException, PersistenceException, RemoteException;
    public void removeQuestionAnswer(Long id) throws BusinessException, PersistenceException, RemoteException;
    public ClosedAnswer getQuestionAnswerById(Long id) throws BusinessException, PersistenceException, RemoteException;
    public List<ClosedAnswer> getAllAnswers() throws BusinessException, PersistenceException, RemoteException;
    public List<ClosedAnswer> getAnswerByUser(Long id) throws BusinessException, PersistenceException, RemoteException;
}
