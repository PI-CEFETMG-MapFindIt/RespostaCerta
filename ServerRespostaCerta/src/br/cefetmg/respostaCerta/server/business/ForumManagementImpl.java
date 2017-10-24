/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.server.business;

import br.cefetmg.respostaCerta.model.domain.Forum;
import br.cefetmg.respostaCerta.model.exception.BusinessException;
import br.cefetmg.respostaCerta.model.exception.PersistenceException;
import br.cefetmg.respostaCerta.model.dao.ForumDAO;
import br.cefetmg.respostaCerta.model.dao.ForumDAOImpl;
import br.cefetmg.respostaCerta.model.dao.TopicDAO;
import br.cefetmg.respostaCerta.model.dao.TopicDAOImpl;
import br.cefetmg.respostaCerta.model.service.ForumManagement;
import br.cefetmg.respostaCerta.model.service.TopicManagement;
import java.rmi.Remote;

/**
 *
 * @author adalbs
 */
public class ForumManagementImpl implements ForumManagement{
    private final ForumManagement forumManagement;
    private final TopicManagement topicManagement;
    
    public ForumManagementImpl(){
        this.topicManagement= new br.cefetmg.respostaCerta.model.service.TopicManagementImpl(new TopicDAOImpl());
        this.forumManagement= new br.cefetmg.respostaCerta.model.service.ForumManagementImpl(new ForumDAOImpl(), new TopicDAOImpl());
    }

    /**
     *
     * @param forum
     * @return 
     * @throws BusinessException
     * @throws PersistenceException
     * 
     */
    @Override
    public void registerForum(Forum forum) throws BusinessException, PersistenceException {
        if(forum==null){
            throw new BusinessException("O forum não pode ser nulo");
        }
        if(forum.getDataCriacao()==null){
            throw new BusinessException("Data de criação não pode ser nula");
        }
        if(forum.getQuestao()==null){
            throw new BusinessException("O forum deve referenciar uma questao");
        }
        forumManagement.registerForum(forum);
    }

    /**
     *
     * @param id // id da questão que terá o forum atualizado
     * @param forum
     * @throws BusinessException
     * @throws PersistenceException
     * Atualiza um forum já existente
     */
    @Override
    public void updateForum(Long id, Forum forum) throws BusinessException, PersistenceException {
        if(id==null){
            throw new BusinessException("Id não pode ser nulo");
        }
        if(forum==null){
            throw new BusinessException("O forum não pode ser nulo");
        }
        if(forum.getDataCriacao()==null){
            throw new BusinessException("Data de criação não pode ser nula");
        }
        if(forum.getQuestao()==null){
            throw new BusinessException("O forum deve referenciar uma questao");
        }
        forumManagement.updateForum(id, forum);
        if(forumManagement.getForumById(id)!=forum){
            throw new PersistenceException("Erro de persistencia ao atualizar");
        }
    }

    /**
     *
     * @param id // id do forum que será deletado
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public void removeForum(Long id) throws BusinessException, PersistenceException {
        if(id==null){
            throw new BusinessException("Id não pode ser nulo");
        }
        forumManagement.removeForum(id);
    }
    /**
     *
     * @param id
     * @return // retorna um objecto do tipo forum, que representa o forum da questão
     * @throws BusinessException
     * @throws PersistenceException
     */
    @Override
    public Forum getForumById(Long id) throws BusinessException, PersistenceException {
       if(id==null){
            throw new BusinessException("Id não pode ser nulo");
        }
        return forumManagement.getForumById(id);
    }
    
}
