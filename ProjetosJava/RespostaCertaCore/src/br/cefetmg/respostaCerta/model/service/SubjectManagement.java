/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;


import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;

/**
 *
 * @author umcan
 */
public interface SubjectManagement {
    public Long registerSubject(Subject subject) throws BusinessException, PersistenceException;
    public void updateSubject(Long id, Subject  subject) throws BusinessException, PersistenceException;
    public void removeSubject(Long id) throws BusinessException, PersistenceException;
    public Subject getSubjectById(Long id) throws BusinessException, PersistenceException;
}
