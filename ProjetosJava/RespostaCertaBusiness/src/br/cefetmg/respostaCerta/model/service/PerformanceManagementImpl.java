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
import br.cefetmg.respostaCerta.model.domain.ClosedAnswer;
import br.cefetmg.respostaCerta.model.dao.ClosedQuestionDAO;
import java.util.List;
/**
 *
 * @author adalbs
 */
public class PerformanceManagementImpl implements PerformanceManagement{
    
    
    private final ClosedAnswerDAO answer;
    private final ModuleDAO module;
    private final SubjectDAO subject;
    private final ClosedQuestionDAO quest;

    /**
     *
     * @param answer
     * @param module
     * @param subject
     * @param quest
     */
    public PerformanceManagementImpl(ClosedAnswerDAO answer, ModuleDAO module, SubjectDAO subject, ClosedQuestionDAO quest) {
        this.answer = answer;
        this.module = module;
        this.subject = subject;
        this.quest = quest;
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
        double totalerros=0;
        if(user == null){
          throw new BusinessException("usuarionão pode ser nulo");  
        }
        if(user.getIdUsuario()==null){
            throw new BusinessException("ID dousuario não pode ser nulo"); 
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
        List<ClosedAnswer> tempq=answer.listAll();
        double totalquestoes=0;
        for (ClosedAnswer q : tempq){
            if(q.getAutor().equals(user)){
                totalquestoes++;
                if(q.getResposta()==quest.getClosedQuestionById(q.getQuestao().getIdQuestao()).getCorreta())
                    totalerros++;
            }
        }
        return (totalerros*100)/totalquestoes;
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
          throw new BusinessException("usuarionão pode ser nulo");  
        }
        if(user.getIdUsuario()==null){
            throw new BusinessException("ID dousuario não pode ser nulo"); 
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
        if(modulo.getDescModulo()==null){
            throw new BusinessException("descrição do modulo não pode ser nulo");
        }
        double totalerros=0;
        double totalquestoes=0;
        List<ClosedAnswer> tempq=answer.listAll();
        for (ClosedAnswer q : tempq){
            if(q.getAutor().equals(user)&&q.getQuestao().getModulo().equals(modulo)){
                totalquestoes++;
                if(q.getResposta()==quest.getClosedQuestionById(q.getQuestao().getIdQuestao()).getCorreta())
                    totalerros++;
            }
        }
        return (totalerros*100)/totalquestoes;
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
    public Double calculateErrosBySubject(User user, Subject disciplina) throws BusinessException, PersistenceException {
        if(user == null){
          throw new BusinessException("usuarionão pode ser nulo");  
        }
        if(user.getIdUsuario()==null){
            throw new BusinessException("ID dousuario não pode ser nulo"); 
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
            throw new BusinessException("subject não pode ser nulo");
        }
        if(disciplina.getNomeDominio()==null){
            throw new BusinessException("Nome do dominio não pode ser nulo");
        }
        if(disciplina.getDescDominio()==null){
            throw new BusinessException("desc do dominio não pode ser nula");
        }
        double totalerros=0;
        double totalquestoes=0;
        List<ClosedAnswer> tempq=answer.listAll();
        for (ClosedAnswer q : tempq){
            if(q.getAutor().equals(user)&&subject.getSubjectById(disciplina.getIdDominio()).equals(disciplina)){
                totalquestoes++;
                if(q.getResposta()==quest.getClosedQuestionById(q.getQuestao().getIdQuestao()).getCorreta())
                    totalerros++;
            }
        }
        return (totalerros*100)/totalquestoes;
        
    }
    
}
