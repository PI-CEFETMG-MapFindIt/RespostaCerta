/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.server.business;

import br.cefetmg.respostaCerta.model.domain.Question;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.dao.OpenQuestionDAOImpl;
import br.cefetmg.respostaCerta.model.server.OpenQuestionManagement;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
/**
 *
 * @author adalbs
 */
public class OpenQuestionManagementImpl implements OpenQuestionManagement{
    
    private final br.cefetmg.respostaCerta.model.service.OpenQuestionManagement questO;

    public OpenQuestionManagementImpl() {
        this.questO = new br.cefetmg.respostaCerta.model.service.OpenQuestionManagementImpl(new OpenQuestionDAOImpl());
    }
    /**
     *
     * @param question
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void registerQuestion(Question question) throws BusinessException, PersistenceException, RemoteException{
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
        questO.registerQuestion(question);
    }

    /**
     *
     * @param id
     * @param question
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void updateQuestion(Long id, Question question) throws BusinessException, PersistenceException, RemoteException{
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
        if(id==null){
            throw new BusinessException("Id não pode ser nulo");
        }
        questO.updateQuestion(id, question);   
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
        questO.removeQuestion(id);
    }

    /**
     *
     * @param id
     * @return
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public Question getQuestionById(Long id) throws BusinessException, PersistenceException, RemoteException{
        if(id==null){
            throw new BusinessException("Id não pode ser nulo");
        }
        return questO.getQuestionById(id);
    }

    @Override
    public List<Question> getQuestionsByUser(Long id) throws BusinessException, PersistenceException, RemoteException{
        if(id==null){
            throw new BusinessException("Id não pode ser nulo");
        }
        List<Question> resp=questO.getQuestionsByUser(id);
        return resp;
    }

    @Override
    public List<Question> searchQuestion(String Parameter) throws BusinessException, PersistenceException, RemoteException{
        if(Parameter.isEmpty()){
            throw new BusinessException("Parametro não pode ser nulo");
        }
       return questO.searchQuestion(Parameter);
    }

    @Override
    public List<Question> getOpenQuestionByModule(Long id) throws BusinessException, PersistenceException, RemoteException{
        if(id==null){
            throw new BusinessException("Id não pode ser nulo");
        }
        return questO.getOpenQuestionByModule(id);
    }

    @Override
    public List<Question> getAllQuestions() throws BusinessException, PersistenceException, RemoteException{
        return questO.getAllQuestions();
    }
    
}
