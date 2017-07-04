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
        List<ClosedAnswer> tempq=answer.getClosedAnswerByUser(user.getIdUsuario());
        double totalquestoes=0;
        double totalerros=0;
        for (ClosedAnswer q : tempq){
            totalquestoes++;
            if(!q.isCorreta())
                totalerros++;
        }
        if(totalquestoes>0)
            return (totalerros*100)/totalquestoes;
        else
            throw new BusinessException("O usuario não respondeu nenhuma questão");
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
        List<ClosedAnswer> tempq=answer.getClosedAnswerByUser(user.getIdUsuario());
        double totalquestoes=0;
        double totalerros=0;
        for (ClosedAnswer q : tempq){
            if(q.getQuestao().getModulo().getIdModulo()==modulo.getIdModulo()){      
                totalquestoes++;
                if(!q.isCorreta())
                    totalerros++;
            }
        }
        if(totalquestoes>0)
            return (totalerros*100)/totalquestoes;
        else
            throw new BusinessException("O usuario não respondeu nenhuma questão desse módulo");
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
        List<ClosedAnswer> tempq=answer.getClosedAnswerByUser(user.getIdUsuario());
        double totalquestoes=0;
        double totalerros=0;
        for (ClosedAnswer q : tempq){
            if(q.getQuestao().getModulo().getDominio().getIdDominio()==disciplina.getIdDominio()){      
                totalquestoes++;
                if(!q.isCorreta())
                    totalerros++;
            }
        }
        if(totalquestoes>0)
            return (totalerros*100)/totalquestoes;
        else
            throw new BusinessException("O usuario não respondeu nenhuma questão desse dominio");
    }
    
}
