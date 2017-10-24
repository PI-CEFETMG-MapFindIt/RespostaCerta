/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.server.business;

import br.cefetmg.respostaCerta.model.domain.Module;
import br.cefetmg.respostaCerta.model.domain.Subject;
import br.cefetmg.respostaCerta.model.domain.User;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.dao.ClosedAnswerDAOImpl;
import br.cefetmg.respostaCerta.model.dao.ClosedQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.dao.ModuleDAOImpl;
import br.cefetmg.respostaCerta.model.dao.SubjectDAOImpl;
import br.cefetmg.respostaCerta.model.service.PerformanceManagement;
/**
 *
 * @author adalbs
 */
public class PerformanceManagementImpl implements PerformanceManagement{
    
    
    private final PerformanceManagement perf;

    /**
     *
     * @param answer
     * @param module
     * @param subject
     * @param quest
     */
    public PerformanceManagementImpl(){
        this.perf = new br.cefetmg.respostaCerta.model.service.PerformanceManagementImpl(new ClosedAnswerDAOImpl(), new ModuleDAOImpl(), new SubjectDAOImpl(), new ClosedQuestionDAOImpl());
    }

    
    /**
     *
     * @param user
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     * retorna o percentual de erros de questões
     */
    @Override
    public Double calculateErrors(User user) throws BusinessException, PersistenceException {
        if(user == null){
          throw new BusinessException("Usuario não pode ser nulo");  
        }
        if(user.getIdUsuario()==null){
            throw new BusinessException("ID do usuario não pode ser nulo"); 
        }
        if(user.getLoginUsuario() == null){
          throw new BusinessException("Login do usuario não pode ser nulo");  
        }
        if(user.getNomeUsuario() == null){
          throw new BusinessException("Nome do usuario não pode ser nulo");  
        }
        if(user.getSenhaUsuario() == null){
          throw new BusinessException("Senha do usuario não pode ser nulo");  
        }
        return perf.calculateErrors(user);
    }

    /**
     *
     * @param user
     * @param modulo
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     * Retorna o percentual de erros por modulo
     */
    @Override
    public Double calculateErrorsByModule(User user, Module modulo) throws BusinessException, PersistenceException {
        if(user == null){
          throw new BusinessException("Usuario não pode ser nulo");  
        }
        if(user.getIdUsuario()==null){
            throw new BusinessException("ID do usuario não pode ser nulo"); 
        }
        if(user.getLoginUsuario() == null){
          throw new BusinessException("Login do usuario não pode ser nulo");  
        }
        if(user.getNomeUsuario() == null){
          throw new BusinessException("Nome do usuario não pode ser nulo");  
        }
        if(user.getSenhaUsuario() == null){
          throw new BusinessException("Senha do usuario não pode ser nulo");  
        }
        if(modulo==null){
            throw new BusinessException("Modulo não pode ser nulo");
        }
        if(modulo.getDominio()==null){
            throw new BusinessException("Dominio não pode ser nulo");
        }
        if(modulo.getNomeModulo()==null){
            throw new BusinessException("Nome do modulo não pode ser nulo");
        }
        return perf.calculateErrorsByModule(user, modulo);
    }

    /**
     *
     * @param user
     * @param disciplina
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     * retorna o percentual de erros por disciplina
     */
    @Override
    public Double calculateErrorsBySubject(User user, Subject disciplina) throws BusinessException, PersistenceException {
        if(user == null){
          throw new BusinessException("Usuario não pode ser nulo");  
        }
        if(user.getIdUsuario()==null){
            throw new BusinessException("ID do usuario não pode ser nulo"); 
        }
        if(user.getLoginUsuario() == null){
          throw new BusinessException("Login do usuario não pode ser nulo");  
        }
        if(user.getNomeUsuario() == null){
          throw new BusinessException("Nome do usuario não pode ser nulo");  
        }
        if(user.getSenhaUsuario() == null){
          throw new BusinessException("Senha do usuario não pode ser nulo");  
        }
        if(disciplina==null){
            throw new BusinessException("Subject não pode ser nulo");
        }
        if(disciplina.getNomeDominio()==null){
            throw new BusinessException("Nome do dominio não pode ser nulo");
        }
        return perf.calculateErrorsBySubject(user, disciplina);
    }
    
}
