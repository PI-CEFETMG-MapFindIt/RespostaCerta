/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.server.business;

import br.cefetmg.respostaCerta.model.dao.ClosedAnswerDAOImpl;
import br.cefetmg.respostaCerta.model.domain.ClosedAnswer;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.server.ClosedAnswerManagement;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author aluno
 */
public class ClosedAnswerManagementImpl implements ClosedAnswerManagement{
    private final br.cefetmg.respostaCerta.model.service.ClosedAnswerManagement closedAnswerManagement;

    public ClosedAnswerManagementImpl() {
        super();
        this.closedAnswerManagement = new br.cefetmg.respostaCerta.model.service.ClosedAnswerManagementImpl(new ClosedAnswerDAOImpl());
    }
    /**
     *
     * @param questionAnswer
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void registerQuestionAnswer(ClosedAnswer questionAnswer) throws BusinessException, PersistenceException, RemoteException{
        if(questionAnswer==null){
            throw new BusinessException("A resposta não pode ser nula");
        }
        if(questionAnswer.getAutor()==null){
            throw new BusinessException("Autor não pode ser nulo");
        }
        if(questionAnswer.getDataResposta()==null){
            throw new BusinessException("Data da resposta não pode ser nula");
        }
        closedAnswerManagement.registerQuestionAnswer(questionAnswer);
        
    }

    /**
     *
     * @param id
     * @param questionAnswer
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void updateQuestionAnswer(Long id, ClosedAnswer questionAnswer) throws BusinessException, PersistenceException, RemoteException{
        if(questionAnswer==null){
            throw new BusinessException("A resposta não pode ser nula");
        }
        if(questionAnswer.getAutor()==null){
            throw new BusinessException("Autor não pode ser nulo");
        }
        if(questionAnswer.getDataResposta()==null){
            throw new BusinessException("Data da resposta não pode ser nula");
        }
        if(id==null){
            throw new BusinessException("ID não pode ser nulo");
        }
        closedAnswerManagement.updateQuestionAnswer(id, questionAnswer);
    }

    /**
     *
     * @param id
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void removeQuestionAnswer(Long id) throws BusinessException, PersistenceException, RemoteException{
        if(id==null){
            throw new BusinessException("ID não pode ser nulo");
        }
        closedAnswerManagement.removeQuestionAnswer(id);
    }

    /**
     *
     * @param id
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public ClosedAnswer getQuestionAnswerById(Long id) throws BusinessException, PersistenceException, RemoteException{
        if(id==null){
            throw new BusinessException("ID não pode ser nulo");
        }
        return closedAnswerManagement.getQuestionAnswerById(id);
    }

    @Override
    public List<ClosedAnswer> getAllAnswers() throws BusinessException, PersistenceException, RemoteException{
        return closedAnswerManagement.getAllAnswers();
    }
    
    @Override
    public List<ClosedAnswer> getAnswerByUser(Long id) throws BusinessException, PersistenceException, RemoteException{
        if(id==null){
            throw new BusinessException("ID não pode ser nulo");
        }
        return closedAnswerManagement.getAnswerByUser(id);
    }
}
