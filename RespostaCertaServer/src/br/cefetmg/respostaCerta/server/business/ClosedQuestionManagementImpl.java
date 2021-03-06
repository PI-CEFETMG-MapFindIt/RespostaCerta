/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.server.business;

import br.cefetmg.respostaCerta.model.dao.ClosedQuestionDAO;
import br.cefetmg.respostaCerta.model.dao.ClosedQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.domain.ClosedQuestion;
import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.server.ClosedQuestionManagement;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author aluno
 */
public class ClosedQuestionManagementImpl implements ClosedQuestionManagement{
    private final br.cefetmg.respostaCerta.model.service.ClosedQuestionManagement questC;

    public ClosedQuestionManagementImpl() {
        super();
        this.questC = new br.cefetmg.respostaCerta.model.service.ClosedQuestionManagementImpl(new ClosedQuestionDAOImpl());
    }
    /**
     *
     * @param question
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void registerQuestion(ClosedQuestion question) throws BusinessException, PersistenceException, RemoteException{
        if(question==null){
            throw new BusinessException("Question não pode ser nulo");
        }
        if(question.getCriador()==null){
            throw new BusinessException("Criador não pode ser nulo");
        }
        if(question.getDataCriacao()==null){
            throw new BusinessException("Data de criação não pode ser nulo");
        }
        if(question.getModulo()==null){
            throw new BusinessException("Modulo não pode ser nulo");
        }
        if(question.getEnunciadoQuestao()==null){
            throw new BusinessException("Enunciado não pode ser nulo");
        }
        if(question.getTituloQuestao()==null){
            throw new BusinessException("Titulo não pode ser nulo");
        }
        if(question.getAlt1()==null || question.getAlt2()==null || question.getAlt3()==null || question.getAlt4()==null || question.getAlt5()==null){
           throw new BusinessException("Alternativas não podem ser nulas"); 
        }
        questC.registerQuestion(question);
    }

    /**
     *
     * @param id
     * @param question
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void updateQuestion(Long id, ClosedQuestion question) throws BusinessException, PersistenceException, RemoteException{
        if(question==null){
            throw new BusinessException("Question não pode ser nulo");
        }
        if(question.getCriador()==null){
            throw new BusinessException("Criador não pode ser nulo");
        }
        if(question.getDataCriacao()==null){
            throw new BusinessException("Data de criação não pode ser nulo");
        }
        if(question.getModulo()==null){
            throw new BusinessException("Modulo não pode ser nulo");
        }
        if(question.getEnunciadoQuestao()==null){
            throw new BusinessException("Enunciado não pode ser nulo");
        }
        if(question.getTituloQuestao()==null){
            throw new BusinessException("Titulo não pode ser nulo");
        }
        if(question.getAlt1()==null || question.getAlt2()==null || question.getAlt3()==null || question.getAlt4()==null || question.getAlt5()==null){
           throw new BusinessException("Alternativas não podem ser nulas"); 
        }
        if(id==null){
            throw new BusinessException("Id não pode ser nulo");
        }
        questC.updateQuestion(id, question);   
    }

    /**
     *
     * @param id
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void removeQuestion(Long id) throws BusinessException, PersistenceException, RemoteException{
        if(id==null){
            throw new BusinessException("Id não pode ser nulo");
        }
        questC.removeQuestion(id);
    }

    /**
     *
     * @param id
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public ClosedQuestion getQuestionById(Long id) throws BusinessException, PersistenceException, RemoteException{
        if(id==null){
            throw new BusinessException("Id não pode ser nulo");
        }
        return questC.getQuestionById(id);
    }

    /**
     *
     * @param id
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     * @throws java.rmi.RemoteException
     */
    @Override
    public List<ClosedQuestion> getQuestionsByUser(Long id) throws BusinessException, PersistenceException, RemoteException{
        if(id==null){
            throw new BusinessException("Id não pode ser nulo");
        }
        List<ClosedQuestion> resp=questC.getQuestionsByUser(id);
        return resp;
    }
    
    @Override
    public List<Question> searchClosedQuestion(String Parameter) throws BusinessException, PersistenceException, RemoteException{
        if(Parameter.isEmpty()){
            throw new BusinessException("Parametro não pode ser nulo");
        }
       return questC.searchClosedQuestion(Parameter);
    }

    @Override
    public List<Question> getClosedQuestionByModule(Long id) throws BusinessException, PersistenceException, RemoteException{
        if(id==null){
            throw new BusinessException("Id não pode ser nulo");
        }
        return questC.getClosedQuestionByModule(id);
    }

    @Override
    public List<ClosedQuestion> getAllQuestions() throws BusinessException, PersistenceException, RemoteException{
        return questC.getAllQuestions();
    }
}
