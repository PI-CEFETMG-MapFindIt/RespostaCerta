/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.server.business;

import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.dao.OpenAnswerDAOImpl;
import br.cefetmg.respostaCerta.model.domain.OpenAnswer;
import br.cefetmg.respostaCerta.model.server.OpenAnswerManagement;
import java.rmi.RemoteException;
import java.util.List;
/**
 *
 * @author adalbs
 */
public class OpenAnswerManagementImpl implements OpenAnswerManagement{
    private final br.cefetmg.respostaCerta.model.service.OpenAnswerManagement openAnswerManagement;

    public OpenAnswerManagementImpl(){
        this.openAnswerManagement = new br.cefetmg.respostaCerta.model.service.OpenAnswerManagementImpl(new OpenAnswerDAOImpl());
    }
    /**
     *
     * @param questionAnswer
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void registerQuestionAnswer(OpenAnswer questionAnswer) throws BusinessException, PersistenceException, RemoteException{
        if(questionAnswer==null){
            throw new BusinessException("A resposta não pode ser nula");
        }
        if(questionAnswer.getAutor()==null){
            throw new BusinessException("Autor não pode ser nulo");
        }
        if(questionAnswer.getDataResposta()==null){
            throw new BusinessException("Data da resposta não pode ser nula");
        }
        openAnswerManagement.registerQuestionAnswer(questionAnswer);
    }

    /**
     *
     * @param id
     * @param questionAnswer
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void updateQuestionAnswer(Long id, OpenAnswer questionAnswer) throws BusinessException, PersistenceException, RemoteException{
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
        openAnswerManagement.updateQuestionAnswer(id, questionAnswer);
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
        openAnswerManagement.removeQuestionAnswer(id);
    }

    /**
     *
     * @param id
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public OpenAnswer getQuestionAnswerById(Long id) throws BusinessException, PersistenceException, RemoteException{
        if(id==null){
            throw new BusinessException("ID não pode ser nulo");
        }
        return openAnswerManagement.getQuestionAnswerById(id);
    }

    @Override
    public List<OpenAnswer> getAllAnswers() throws BusinessException, PersistenceException, RemoteException{
        return openAnswerManagement.getAllAnswers();
    }
    
}
