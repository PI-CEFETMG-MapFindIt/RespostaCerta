/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.server;


import br.cefetmg.respostaCerta.model.service.*;
import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author umcan
 */
public interface SubjectManagement extends Remote{
    public void registerSubject(Subject subject) throws BusinessException, PersistenceException, RemoteException;
    public void updateSubject(Long id, Subject  subject) throws BusinessException, PersistenceException, RemoteException;
    public void removeSubject(Long id) throws BusinessException, PersistenceException, RemoteException;
    public Subject getSubjectById(Long id) throws BusinessException, PersistenceException, RemoteException;
    public List<Subject> getAllSubjects() throws BusinessException, PersistenceException, RemoteException;
    public List<Subject> searchSubjects(String busca) throws BusinessException, PersistenceException, RemoteException;
}
