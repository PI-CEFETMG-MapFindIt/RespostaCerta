/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.server.business;

import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.dao.SubjectDAOImpl;
import br.cefetmg.respostaCerta.model.service.SubjectManagement;
import java.util.List;
/**
 *
 * @author adalbs
 */
public class SubjectManagementImpl implements SubjectManagement{

    private final SubjectManagement sub;

    public SubjectManagementImpl(){
        this.sub = new br.cefetmg.respostaCerta.model.service.SubjectManagementImpl(new SubjectDAOImpl());
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
        sub.registerSubject(subject);
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
        sub.updateSubject(id, subject);
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
        sub.removeSubject(id);
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
        return sub.getSubjectById(id);
    }

    @Override
    public List<Subject> getAllSubjects() throws BusinessException, PersistenceException {
        return sub.getAllSubjects();
    }
    
    @Override
    public List<Subject> searchSubjects(String busca) throws BusinessException, PersistenceException {
        if(busca==null || busca.equals("")){
            throw new BusinessException("Busca Vazia");
        }
        return sub.searchSubjects(busca);
    }
}
