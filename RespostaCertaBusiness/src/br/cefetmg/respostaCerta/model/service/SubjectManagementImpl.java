/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.service;

import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.dao.SubjectDAO;
/**
 *
 * @author adalbs
 */
public class SubjectManagementImpl implements SubjectManagement{

    private final SubjectDAO subjectDAO;

    public SubjectManagementImpl(SubjectDAO subjectDAO) {
        this.subjectDAO = subjectDAO;
    }
    
    
    /**
     *
     * @param subject
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void registerSubject(Subject subject) throws BusinessException, PersistenceException {
        if(subject==null){
            throw new BusinessException("Subject não pode ser nulo");
        }
        if(subject.getNomeDominio()==null){
            throw new BusinessException("Nome do dominio não pode ser nulo");
        }
        if( subject.getDescDominio()==null){
            throw new BusinessException("Desc do dominio não pode ser nula");
        }
        subjectDAO.insert(subject);
    }

    /**
     *
     * @param id
     * @param subject
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void updateSubject(Long id, Subject subject) throws BusinessException, PersistenceException {
        if(id == null){
            throw new BusinessException("ID não pode ser nulo");
        }
        if(subject==null){
            throw new BusinessException("Subject não pode ser nulo");
        }
        if(subject.getNomeDominio()==null){
            throw new BusinessException("Nome do dominio não pode ser nulo");
        }
        if( subject.getDescDominio()==null){
            throw new BusinessException("Desc do dominio não pode ser nula");
        }
        subject.setIdDominio(id);
        subjectDAO.update(subject);
    }

    /**
     *
     * @param id
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void removeSubject(Long id) throws BusinessException, PersistenceException {
        if(id == null){
            throw new BusinessException("ID não pode ser nulo");
        }
        subjectDAO.delete(id);
    }

    /**
     *
     * @param id
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public Subject getSubjectById(Long id) throws BusinessException, PersistenceException {
        if(id == null){
            throw new BusinessException("ID não pode ser nulo");
        }
        return subjectDAO.getSubjectById(id);
    }
    
}
